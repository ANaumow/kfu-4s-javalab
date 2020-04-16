<#macro out post>
<#-- @ftlvariable name="post" type="ru.naumow.entity.Post" -->
    <div id="post-holder-${post.id}" class="custom-post-section"
         style="margin-top: 15px;background-color: #ffffff;margin-bottom: 15px;padding-bottom: 5px; padding-top: 7px;">
        <div id="content-section-${post.id}">
        </div>
        <div id="action-section" class="d-flex d-sm-flex d-md-flex justify-content-end align-items-center justify-content-sm-end align-items-sm-center justify-content-md-end align-items-md-center borderer-up" style="padding: 2px;">
            <div id="comment-icon"
                 onclick="getComments('comments-section-post-${post.id}', ${post.id})"
                 style="cursor:pointer;">
                <i class="fas fa-comment-alt"
                   style="padding: 6px;font-size: 15px;color: rgb(132,132,132);font-weight: normal;font-style: normal;"></i>
            </div>
            <div id="like-icon" onclick="alert('like')" style="cursor:pointer;">
                <i class="far fa-heart unliked" style="padding: 6px;font-size: 15px;"></i>
            </div>
        </div>

        <script type="text/javascript">


            $(function () {
                $.get('${post.content.url}', function (md) {
                    // это кароче использование стороней библиотеки для постов моего блога
                    // оно не играет никакой роли для лонг полинга
                    editormd.urls.atLinkBase = "/";
                    editormd.markdownToHTML("content-section-${post.id}", {
                        markdown: md,
                        htmlDecode: "style,script,iframe",
                        tocm: true,
                        emoji: true,
                        taskList: true,
                        tex: true,
                        flowChart: true,
                        sequenceDiagram: true,
                    });
                });
            });
        </script>

        <script type="text/javascript">
            function sendComment(id, postId) {
                let text = $('textarea#' + id).val();
                /*alert(text);*/
                $.post("/comments", {
                    text: text,
                    postId: postId
                });
            }
        </script>

        <script type="text/javascript">
            function getComments(divId, postId, wait) {
                /*if (wait != null) {
                    $.get("comments", {
                        post_id: postId,
                        w: 1
                    }, function (data) {
                        $("#" + divId).html(data);
                        getComments(divId, postId, 1)
                    })
                } else {
                    $.get("comments", {
                        post_id: postId
                    }, function (data) {
                        $("#" + divId).html(data);
                        getComments(divId, postId, 1)

                    })
                }*/

                let data;
                if (wait != null) {
                    data = {
                        post_id: postId,
                        w: 1
                    }
                } else {
                    data = {
                        post_id: postId
                    }
                }

                $.get("comments", data, function (response) {
                    $("#" + divId).html(response);
                    getComments(divId, postId, 1)
                })
            }

        </script>


        <div id="comments-section-post-${post.id}">
        </div>
    </div>

</#macro>

