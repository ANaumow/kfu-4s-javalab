<div class="borderer-up">



    <#if commentList?size gt 0 >
        <p style="margin: 10px;">Comments</p>
    </#if>


    <div id="post-comment" style="margin-top: 3px;margin-bottom: 3px;">
        <#list commentList as comment>
            <div class="row" style="margin-left: 0;margin-right: 0;">
                <div class="col d-lg-flex justify-content-lg-end align-items-lg-start"
                     style="max-width: 20%;padding-top: 10px;">
                    <div class="border rounded-circle"
                         style="background-image: url(/resources/img/avatar.jpg);background-color: rgba(150,106,39,0.6);background-size: cover;background-position: bottom;background-repeat: no-repeat;height: 40px;width: 40px;"></div>
                </div>
                <div class="col align-self-start" style="max-width: 80%;">
                    <p class="text-left"
                       style="font-size: 15px;filter: blur(0px);font-style: normal;font-weight: bold;margin: 0;">${comment.user.name} ${comment.user.surname}</p>
                    <p class="text-break text-left" style="font-size: 14px;">${comment.text}
                    </p>
                </div>
            </div>
        </#list>
    </div>

    <div id="post-write-section" style="margin-top: 3px;margin-bottom: 3px;">
        <div class="row" style="margin-left: 0;margin-right: 0;">
            <div class="col d-lg-flex justify-content-lg-end align-items-lg-start"
                 style="max-width: 20%;padding-top: 10px;">
                <div class="border rounded-circle"
                     style="background-image: url(/resources/img/avatar.jpg);background-color: rgba(150,106,39,0.6);background-size: cover;background-position: bottom;background-repeat: no-repeat;height: 50px;width: 50px;"></div>
            </div>
            <div class="col d-lg-flex justify-content-center align-items-center justify-content-lg-center align-items-lg-center"
                 style="max-width: 70%;"><textarea id="text-area-post-${post.id}"
                                                   class="form-control-sm"
                                                   style="min-width: 100%;margin-top: 4px;height: 39px;">

                </textarea>
            </div>
            <div class="col d-lg-flex justify-content-lg-start align-items-lg-center"
                 style="max-width: 10%;padding-right: 0px;padding-left: 0px; cursor: pointer"
            onclick="sendComment('text-area-post-' + ${post.id}, ${post.id});">
                <div id="send-button-${post.id}">
                    <i class="fas fa-paper-plane" style="color: rgb(122,122,122);font-size: 20px;"></i>
                </div>
            </div>
        </div>
    </div>
</div>