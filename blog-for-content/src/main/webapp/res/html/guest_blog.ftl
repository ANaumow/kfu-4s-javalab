<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <#--    <link rel="stylesheet" href="./../post/examples/css/style.css"/>-->
    <link rel="stylesheet" href="./../post/css/editormd.css"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

    <title>Blog</title>
</head>
<body style="background-color: rgb(249,249,249);">

<script src="./../post/examples/js/jquery.min.js"></script>
<script src="./../post/lib/marked.min.js"></script>
<script src="./../post/lib/prettify.min.js"></script>

<script src="./../post/lib/raphael.min.js"></script>
<script src="./../post/lib/underscore.min.js"></script>
<script src="./../post/lib/sequence-diagram.min.js"></script>
<script src="./../post/lib/flowchart.min.js"></script>
<script src="./../post/lib/jquery.flowchart.min.js"></script>

<script src="./../post/editormd.js"></script>


<div class="text-justify">
    <div style="background-image: url('./res/html/sssd.jpg');background-position: center;height: 225px;background-size: cover;background-repeat: no-repeat;"></div>
</div>

<div style="margin-right: 25%;margin-left: 25%;margin-top: -48px;">
    <div style="background-color: #ffffff;">
        <div>
            <div>
                <div class="border rounded border-secondary pulse animated"
                     style="width: 100%;margin: 0px;background-color: #ffffff;padding: 12px;">
                    <div class="row" style="margin: 0;width: 100%;">
                        <div class="col d-sm-flex justify-content-sm-center align-items-sm-center justify-content-lg-end"
                             style="max-width: 30%;">
                            <div class="border rounded-circle"
                                 style="background-image: url('./res/html/dsf.jpg');background-color: rgba(150,106,39,0.6);background-size: cover;background-position: bottom;background-repeat: no-repeat;height: 75px;width: 75px;"></div>
                        </div>
                        <div class="col d-flex flex-column justify-content-lg-center"
                             style="padding: 0;max-width: 70%;">
                            <div class="row d-flex d-lg-flex flex-column" style="margin: 0;min-height: 50%;">
                                <div class="col" style="padding: 0;">
                                    <p class="text-left"
                                       style="font-size: 23px;filter: blur(0px);font-style: normal;font-weight: bold;margin: 0;">${userDto.name} ${userDto.surname}</p>
                                </div>
                                <div class="col" style="padding: 0;margin-top: -10px;">
                                    <p style="margin: 0;padding: 0;">${userDto.vocation}</p>
                                </div>
<#--                                <a href="./post/editor">create new post</a>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="margin-top: 13px;">
        <div>
            <#list postList as post>
                <div id="post-${post.id}" class="border rounded border-secondary" style="margin-top: 17px;background-color: #ffffff;">

                    <script type="text/javascript">
                        /*$(function () {
                            editormd.defaults.markdown = "# Hi!";
                            editormd.markdownToHTML("yourDivName");
                            /!*$('#yourDivName').append('<p>hi</p>');*!/
                        })*/

                        $(function () {

                            $.get('${post.content.url}', function (md) {
                                editormd.urls.atLinkBase = "http://localhost:8080/";

                                editormd.markdownToHTML("post-${post.id}", {
                                    markdown: md,//"# HERE WE GO ",//+ "\r\n" + $("#append-test").text(),
                                    //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                                    htmlDecode: "style,script,iframe",  // you can filter tags decode
                                    //toc             : false,
                                    tocm: true,    // Using [TOCM]
                                    //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
                                    //gfm             : false,
                                    //tocDropdown     : true,
                                    // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                                    emoji: true,
                                    taskList: true,
                                    tex: true,  // 默认不解析
                                    flowChart: true,  // 默认不解析
                                    sequenceDiagram: true,  // 默认不解析
                                });

                                //console.log("返回一个 jQuery 实例 =>", testEditormdView);

                                // 获取Markdown源码
                                //console.log(testEditormdView.getMarkdown());

                                //alert(testEditormdView.getMarkdown());
                            });
                        });
                    </script>

                    <#--<div style="padding: 9px;">
                        <h3><strong>Звездная ночь</strong><br></h3>
                    </div>
                    <div style="max-width: 100%;max-height: 100%;min-height: 100%;min-width: 100%;height: 100%;width: 100%;"><img src="assets/img/IMG_148926-688x430.jpg" width="100%" height="100%"></div>
                    <div style="padding: 9px;">
                        <p style="margin: 0;"><strong>Ля какую картину написал</strong></p>
                    </div>-->
                </div>
            </#list>
        </div>
    </div>
</div>

<#--<div>
    <p>Blog:</p>
    <p>${blogDto.title}</p>
    <p>Author:</p>
    <p>${userDto.name} ${userDto.surname}</p>
    <p>${userDto.vocation}</p>
</div>-->
<#--
<div>
    <p>feed</p>


    <div id="yourDivName">

        <#list postList as post>
            <div>


                &lt;#&ndash;<script type="text/javascript">
                    /*$(function () {
                        editormd.defaults.markdown = "# Hi!";
                        editormd.markdownToHTML("yourDivName");
                        /!*$('#yourDivName').append('<p>hi</p>');*!/
                    })*/

                    $(function () {

                        $.get('${post.content.url}', function (md) {
                            editormd.urls.atLinkBase = "http://localhost:8080/";
                            alert(md);
                            editormd.markdownToHTML("yourDivName", {
                                markdown: md,//"# HERE WE GO ",//+ "\r\n" + $("#append-test").text(),
                                //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                                htmlDecode: "style,script,iframe",  // you can filter tags decode
                                //toc             : false,
                                tocm: true,    // Using [TOCM]
                                //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
                                //gfm             : false,
                                //tocDropdown     : true,
                                // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                                emoji: true,
                                taskList: true,
                                tex: true,  // 默认不解析
                                flowChart: true,  // 默认不解析
                                sequenceDiagram: true,  // 默认不解析
                            });

                            //console.log("返回一个 jQuery 实例 =>", testEditormdView);

                            // 获取Markdown源码
                            //console.log(testEditormdView.getMarkdown());

                            //alert(testEditormdView.getMarkdown());
                        });
                    });
                </script>&ndash;&gt;
            </div>
        </#list>

    </div>

</div>-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>

</html>