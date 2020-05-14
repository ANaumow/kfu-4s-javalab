/*!
 * Image (upload) dialog plugin for Editor.md
 *
 * @file        image-dialog.js
 * @author      pandao
 * @version     1.3.4
 * @updateTime  2015-06-09
 * {@link       https://github.com/pandao/editor.md}
 * @license     MIT
 */

(function () {

    var factory = function (exports) {

        var pluginName = "image-dialog";

        exports.fn.imageDialog = function () {

            var _this = this;
            var cm = this.cm;
            var lang = this.lang;
            var editor = this.editor;
            var settings = this.settings;
            var cursor = cm.getCursor();
            var selection = cm.getSelection();
            var imageLang = lang.dialog.image;
            var classPrefix = this.classPrefix;
            var iframeName = classPrefix + "image-iframe";
            var dialogName = classPrefix + pluginName, dialog;

            cm.focus();

            var loading = function (show) {
                var _loading = dialog.find("." + classPrefix + "dialog-mask");
                _loading[(show) ? "show" : "hide"]();
            };

            if (editor.find("." + dialogName).length < 1) {
                var guid = (new Date).getTime();
                var action = settings.imageUploadURL + (settings.imageUploadURL.indexOf("?") >= 0 ? "&" : "?") + "guid=" + guid;
                console.log("ну это получилось?");
                console.log(settings.csrfHeader + " " + settings.csrfToken);

                if (settings.crossDomainUpload) {
                    action += "&callback=" + settings.uploadCallbackURL + "&dialog_id=editormd-image-dialog-" + guid;
                }

                var dialogContent = ((settings.imageUpload) ? "<form action=\"" + action + "\" target=\"" + iframeName + "\" method=\"post\" enctype=\"multipart/form-data\" class=\"" + classPrefix + "form\">" : "<div class=\"" + classPrefix + "form\">") +
                    ((settings.imageUpload) ? "<iframe name=\"" + iframeName + "\" id=\"" + iframeName + "\" guid=\"" + guid + "\"></iframe>" : "") +
                    "<label>" + imageLang.url + "</label>" +
                    "<input type=\"text\" data-url />" + (function () {
                        return (settings.imageUpload) ? "<div class=\"" + classPrefix + "file-input\">" +
                            "<input id=\"image-input-id\" type=\"file\" name=\"" + classPrefix + "image-file\" accept=\"image/*\" />" +
                            "<input type='hidden' name=" + settings.csrfHeader + " value=" + settings.csrfToken + "/>" +
                            "<input type=\"button\" value=\"" + imageLang.uploadButton + "\" />" +
                            "</div>" : "";
                    })() +
                    "<br/>" +
                    "<label>" + imageLang.alt + "</label>" +
                    "<input type=\"text\" value=\"" + selection + "\" data-alt />" +
                    "<br/>" +
                    "<label>" + imageLang.link + "</label>" +
                    "<input type=\"text\" value=\"http://\" data-link />" +
                    "<br/>" +
                    ((settings.imageUpload) ? "</form>" : "</div>");

                //var imageFooterHTML = "<button class=\"" + classPrefix + "btn " + classPrefix + "image-manager-btn\" style=\"float:left;\">" + imageLang.managerButton + "</button>";

                dialog = this.createDialog({
                    title: imageLang.title,
                    width: (settings.imageUpload) ? 465 : 380,
                    height: 254,
                    name: dialogName,
                    content: dialogContent,
                    mask: settings.dialogShowMask,
                    drag: settings.dialogDraggable,
                    lockScreen: settings.dialogLockScreen,
                    maskStyle: {
                        opacity: settings.dialogMaskOpacity,
                        backgroundColor: settings.dialogMaskBgColor
                    },
                    buttons: {
                        enter: [lang.buttons.enter, function () {
                            var url = this.find("[data-url]").val();
                            var alt = this.find("[data-alt]").val();
                            var link = this.find("[data-link]").val();

                            if (url === "") {
                                alert(imageLang.imageURLEmpty);
                                return false;
                            }

                            var altAttr = (alt !== "") ? " \"" + alt + "\"" : "";

                            if (link === "" || link === "http://") {
                                cm.replaceSelection("![" + alt + "](" + url + altAttr + ")");
                            } else {
                                cm.replaceSelection("[![" + alt + "](" + url + altAttr + ")](" + link + altAttr + ")");
                            }

                            if (alt === "") {
                                cm.setCursor(cursor.line, cursor.ch + 2);
                            }

                            this.hide().lockScreen(false).hideMask();

                            //删除对话框
                            this.remove();

                            return false;
                        }],

                        cancel: [lang.buttons.cancel, function () {
                            this.hide().lockScreen(false).hideMask();

                            //删除对话框
                            this.remove();

                            return false;
                        }]
                    }
                });

                /*jQuery.loadScript = function (url, callback) {
                    jQuery.ajax({
                        url: url,
                        dataType: 'script',
                        success: callback,
                        async: true
                    });
                }*/

                //asdfasdfsafafa
                $(function () {
                    console.log("d");
                    console.log($("#image-input-id"));

                    function loadScript(url, callback) {

                        var script = document.createElement("script")
                        script.type = "text/javascript";

                        if (script.readyState) {  //IE
                            script.onreadystatechange = function () {
                                if (script.readyState == "loaded" ||
                                    script.readyState == "complete") {
                                    script.onreadystatechange = null;
                                    callback();
                                }
                            };
                        } else {  //Others
                            script.onload = function () {
                                callback();
                            };
                        }

                        script.src = url;
                        document.getElementsByTagName("head")[0].appendChild(script);
                    }


                    loadScript("https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js", function () {
                        loadScript("https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js", function () {
                            loadScript('https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/10.24.0/js/jquery.fileupload.min.js', function () {

                                var token = $("meta[name='_csrf']").attr("content");
                                var header = $("meta[name='_csrf_header']").attr("content");

                                /* console.log(token)
                                 $.ajaxSetup({
                                     beforeSend: function (xhr) {
                                         xhr.setRequestHeader(header, token);
                                     }
                                 });

                                 let data = {};

                                 data[settings.csrfHeader] = settings.csrfToken;
                                 console.log(data)

                                 console.log("all scripts loaded")
                                 $("#image-input-id").fileupload({
                                     paramName: 'editormd-image-file',
                                     maxFileSize: 264000,
                                     formData: [{
                                         name: 'X-CSRF-TOKEN',
                                         value: token
                                     }],
                                     uploadUrl: action/!*"{{url('/rate/uploadfile')}}"*!/,
                                     uploadAsync: true,
                                     data,
                                     maxFileCount: 1,
                                     showUpload: true,
                                     dropZoneEnabled: false
                                 });*/
                            })
                        })
                    });


                });

                dialog.attr("id", classPrefix + "image-dialog-" + guid);

                if (!settings.imageUpload) {
                    return;
                }

                var fileInput = dialog.find("[name=\"" + classPrefix + "image-file\"]");

                fileInput.bind("change", function () {
                    var fileName = fileInput.val();
                    var isImage = new RegExp("(\\.(" + settings.imageFormats.join("|") + "))$", "i"); // /(\.(webp|jpg|jpeg|gif|bmp|png))$/

                    if (fileName === "") {
                        alert(imageLang.uploadFileEmpty);

                        return false;
                    }

                    if (!isImage.test(fileName)) {
                        alert(imageLang.formatNotAllowed + settings.imageFormats.join(", "));

                        return false;
                    }

                    //loading(true);

                    var submitHandler = function () {

                        var uploadIframe = document.getElementById(iframeName)


                        console.log(iframeName);


                        uploadIframe.onload = function () {


                            console.log("ass " + uploadIframe);

                            //loading(false);

                            var body = (uploadIframe.contentWindow ? uploadIframe.contentWindow : uploadIframe.contentDocument).document.body;
                            var json = (body.innerText) ? body.innerText : ((body.textContent) ? body.textContent : null);


                            json = (typeof JSON.parse !== "undefined") ? JSON.parse(json) : eval("(" + json + ")");

                            if (!settings.crossDomainUpload) {
                                if (json.success === 1) {
                                    dialog.find("[data-url]").val(json.url);
                                } else {
                                    alert(json.message);
                                }
                            }

                            return false;
                        };
                    };

                    dialog.find("[type=\"button\"]").bind("click", function () {

                        let formData = new FormData();
                        // забрал файл из input
                        let files = ($('#image-input-id'))[0]['files'];
                        // добавляю файл в formData
                        [].forEach.call(files, function (file, i, files) {
                            console.log(file);
                            formData.append("editormd_image_file", file);
                        });

                        // console.log(settings.csrfHeader + " " + settings.csrfToken);

                        formData.append(settings.csrfHeader, settings.csrfToken);
                        console.log("sending ..")


                        var token = $("meta[name='_csrf']").attr("content");
                        var header = $("meta[name='_csrf_header']").attr("content");

                        console.log(token)
                        $.ajaxSetup({
                            beforeSend: function (xhr) {
                                xhr.setRequestHeader(header, token);
                            }
                        });


                        $.ajax({
                            type: "POST",
                            url: action,
                            data: formData,
                            processData: false,
                            contentType: false,

                            success: function (response) {

                                //var body = response; //(uploadIframe.contentWindow ? uploadIframe.contentWindow : uploadIframe.contentDocument).document.body;
                                var json = response//eval("(" + response + ")");//JSON.parse(text);//(body.innerText) ? body.innerText : ((body.textContent) ? body.textContent : null);
                                console.log(json);

                                //json = (typeof JSON.parse !== "undefined") ? JSON.parse(json) : eval("(" + json + ")");

                                if (!settings.crossDomainUpload) {
                                    if (json.success === 1) {
                                        dialog.find("[data-url]").val(json.url);
                                    } else {
                                        alert(json.message);
                                    }
                                }
                            }
                        });
                    }).trigger("click");
                });
            }

            dialog = editor.find("." + dialogName);
            dialog.find("[type=\"text\"]").val("");
            dialog.find("[type=\"file\"]").val("");
            dialog.find("[data-link]").val("http://");

            this.dialogShowMask(dialog);
            this.dialogLockScreen();
            dialog.show();

        };

    };

    // CommonJS/Node.js
    if (typeof require === "function" && typeof exports === "object" && typeof module === "object") {
        module.exports = factory;
    } else if (typeof define === "function")  // AMD/CMD/Sea.js
    {
        if (define.amd) { // for Require.js

            define(["editormd"], function (editormd) {
                factory(editormd);
            });

        } else { // for Sea.js
            define(function (require) {
                var editormd = require("../../editormd");
                factory(editormd);
            });
        }
    } else {
        factory(window.editormd);
    }

})();
