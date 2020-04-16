<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8"/>
    <title>Editor</title>
<#--    <link rel="stylesheet" href="/editor/examples/css/style.css"/>-->
    <link rel="stylesheet" href="/editor/css/editormd.css"/>
    <#--<link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon"/>-->
    <style>
        /*.editormd-preview-theme-dark {
            color: #777;
            background:#2C2827;
        }

        .editormd-preview-theme-dark .editormd-toc-menu > .markdown-toc {
            background:#fff;
            border:none;
        }

        .editormd-preview-theme-dark .editormd-toc-menu > .markdown-toc h1{
            border-color:#ddd;
        }

        .editormd-preview-theme-dark .markdown-body h1,
        .editormd-preview-theme-dark .markdown-body h2,
        .editormd-preview-theme-dark .markdown-body hr {
            border-color: #222;
        }

        .editormd-preview-theme-dark .editormd-preview-container  blockquote {
            color: #555;
            border-color: #333;
            background: #222;
            padding: 0.5em;
        }

        .editormd-preview-theme-dark .editormd-preview-container abbr {
            background:#ff9900;
            color: #fff;
            padding: 1px 3px;
            border-radius: 3px;
        }

        .editormd-preview-theme-dark .editormd-preview-container code {
            background: #5A9600;
            color: #fff;
            border: none;
            padding: 1px 3px;
            border-radius: 3px;
        }

        .editormd-preview-theme-dark .editormd-preview-container table {
            border: none;
        }

        .editormd-preview-theme-dark .editormd-preview-container .fa-emoji {
            color: #B4BF42;
        }

        .editormd-preview-theme-dark .editormd-preview-container .katex {
            color: #FEC93F;
        }

        .editormd-preview-theme-dark [class*=editormd-logo] {
            color: #2196F3;
        }

        .editormd-preview-theme-dark .sequence-diagram text {
            fill: #fff;
        }

        .editormd-preview-theme-dark .sequence-diagram rect,
        .editormd-preview-theme-dark .sequence-diagram path {
            color:#fff;
            fill : #64D1CB;
            stroke : #64D1CB;
        }

        .editormd-preview-theme-dark .flowchart rect,
        .editormd-preview-theme-dark .flowchart path {
            stroke : #A6C6FF;
        }

        .editormd-preview-theme-dark .flowchart rect {
            fill: #A6C6FF;
        }

        .editormd-preview-theme-dark .flowchart text {
            fill: #5879B4;
        }*/
    </style>
</head>
<body>
<div id="layout">
    <header>
        <h1>Themes</h1>
        <p>
            <select id="editormd-theme-select">
                <option selected="selected" value="">select Editor.md themes</option>
            </select>
            <select id="editor-area-theme-select">
                <option selected="selected" value="">select editor area themes</option>
            </select>
            <select id="preview-area-theme-select">
                <option selected="selected" value="">select preview area themes</option>
            </select>
        </p>
        <h1>Crete new post</h1>
        <#--<p>Full example</p>
        <ul style="margin: 10px 0 0 18px;">
            <li>Enable HTML tags decode</li>
            <li>Enable TeX, Flowchart, Sequence Diagram, Emoji, FontAwesome, Task lists</li>
            <li>Enable Image upload</li>
            <li>Enable [TOCM], Search Replace, Code fold</li>
        </ul>-->
    </header>
    <div class="btns">
        <button id="goto-line-btn">Goto line 90</button>
        <button id="show-btn">Show editor</button>
        <button id="hide-btn">Hide editor</button>
        <button id="get-md-btn">Get Markdown</button>
        <button id="get-html-btn">Get HTML</button>
        <button id="send-html">Submit post</button>
        <button id="watch-btn">Watch</button>
        <button id="unwatch-btn">Unwatch</button>
        <button id="preview-btn">Preview HTML (Press Shift + ESC cancel)</button>
        <button id="fullscreen-btn">Fullscreen (Press ESC cancel)</button>
        <button id="show-toolbar-btn">Show toolbar</button>
        <button id="close-toolbar-btn">Hide toolbar</button>
        <button id="toc-menu-btn">ToC Dropdown menu</button>
        <button id="toc-default-btn">ToC default</button>
    </div>
    <div id="test-editormd"></div>
</div>
<script src="/editor/js/jquery.min.js"></script>
<script src="/editor/editormd.js"></script>
<script src="/editor/languages/en.js"></script>
<script type="text/javascript" charset="UTF-8">
    var testEditor;

    function themeSelect(id, themes, lsKey, callback) {
        var select = $("#" + id);

        for (var i = 0, len = themes.length; i < len; i++) {
            var theme = themes[i];
            var selected = (localStorage[lsKey] == theme) ? " selected=\"selected\"" : "";

            select.append("<option value=\"" + theme + "\"" + selected + ">" + theme + "</option>");
        }

        select.bind("change", function () {
            var theme = $(this).val();

            if (theme === "") {
                alert("theme == \"\"");
                return false;
            }

            console.log("lsKey =>", lsKey, theme);

            localStorage[lsKey] = theme;
            callback(select, theme);
        });

        return select;
    }

    $(function () {

        $.get('/editor/start.md', function (md) {

            editormd.urls.atLinkBase = "/";

            testEditor = editormd("test-editormd", {
                /*lang: "en",*/
                width: "90%",
                height: 740,
                path: '/editor/lib/',
                /*theme: "dark",
                previewTheme: "dark",
                editorTheme: "pastel-on-dark",*/
                markdown: md,
                /*crossDomainUpload : true,*/
                codeFold: true,
                //syncScrolling : false,
                saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
                searchReplace: true,
                //watch : false,                // 关闭实时预览
                htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
                //toolbar  : false,             //关闭工具栏
                //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                emoji: true,
                taskList: true,
                tocm: true,         // Using [TOCM]
                tex: true,                   // 开启科学公式TeX语言支持，默认关闭
                flowChart: true,             // 开启流程图支持，默认关闭
                sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
                //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "/editor/save-image",

                onload: function () {
                    console.log('onload', this);

                    let path = "/editor/languages/";
                    let value = "en";

                    editormd.loadScript(path + value, function () {
                        testEditor.lang = editormd.defaults.lang;

                        // 只重建涉及语言包的部分，如工具栏、弹出对话框等
                        testEditor.recreate();

                        // 整个编辑器重建，预览HTML会重新生成，出现闪动
                        //testEditor = editormd("test-editormd", {
                        //width: "90%",
                        //height: 640,
                        //path : '../lib/'
                        //});

                        lang = value;
                        console.log(lang, value, editormd.defaults.lang);
                    });


                    //this.fullscreen();
                    //this.unwatch();
                    //this.watch().fullscreen();

                    //this.setMarkdown("#PHP");
                    //this.width("100%");
                    //this.height(480);
                    //this.resize("100%", 640);
                }
            });
        });

        themeSelect("editormd-theme-select", editormd.themes, "theme", function ($this, theme) {
            testEditor.setTheme(theme);
        });

        themeSelect("editor-area-theme-select", editormd.editorThemes, "editorTheme", function ($this, theme) {
            testEditor.setCodeMirrorTheme(theme);
            // or testEditor.setEditorTheme(theme);
        });

        themeSelect("preview-area-theme-select", editormd.previewThemes, "previewTheme", function ($this, theme) {
            testEditor.setPreviewTheme(theme);
        });

        $("#goto-line-btn").bind("click", function () {
            testEditor.gotoLine(90);
        });

        $("#show-btn").bind('click', function () {
            testEditor.show();
        });

        $("#hide-btn").bind('click', function () {
            testEditor.hide();
        });

        $("#get-md-btn").bind('click', function () {
            alert(testEditor.getMarkdown());
        });

        $("#get-html-btn").bind('click', function () {
            alert(testEditor.getHTML());
        });

        $("#send-html").bind('click', function () {
            alert("sending...");

            let formData = new FormData();

            formData.append("editormd_mark_down_text", testEditor.getHTML());

            $.ajax({
                processData: false,
                contentType: false,
                type: "POST",
                url: "/editor/submit",
                data: formData //{editormd_mark_down_text: testEditor.getHTML()}
            }).done(function (response) {
                document.location.href = '/${blog_alias}'
            }).fail(function () {
                alert('Error')
            });
        });
        $("#watch-btn").bind('click', function () {
            testEditor.watch();
        });

        $("#unwatch-btn").bind('click', function () {
            testEditor.unwatch();
        });

        $("#preview-btn").bind('click', function () {
            testEditor.previewing();
        });

        $("#fullscreen-btn").bind('click', function () {
            testEditor.fullscreen();
        });

        $("#show-toolbar-btn").bind('click', function () {
            testEditor.showToolbar();
        });

        $("#close-toolbar-btn").bind('click', function () {
            testEditor.hideToolbar();
        });

        $("#toc-menu-btn").click(function () {
            testEditor.config({
                tocDropdown: true,
                tocTitle: "目录 Table of Contents",
            });
        });

        $("#toc-default-btn").click(function () {
            testEditor.config("tocDropdown", false);
        });
    });
</script>
</body>
</html>