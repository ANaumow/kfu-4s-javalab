<#-- @ftlvariable name="postId" type="java.lang.String" -->
<div id="post-write-section-${postId}" style="margin-top: 3px;margin-bottom: 3px;">
    <div class="row d-xl-flex align-items-xl-start" style="margin-left: 0;margin-right: 0;">
        <div class="col d-sm-flex d-md-flex d-lg-flex justify-content-sm-end align-items-sm-start justify-content-md-end align-items-md-start justify-content-lg-end align-items-lg-start" style="max-width: 20%;padding: 10px;">
            <div class="border rounded-circle" style="background-image: url('/resources/img/avatar.jpg');background-color: rgba(150,106,39,0.6);background-size: cover;background-position: bottom;background-repeat: no-repeat;height: 40px;width: 40px;"></div>
        </div>
        <div class="col d-lg-flex justify-content-center align-items-center justify-content-lg-center align-items-lg-center" style="max-width: 70%;padding: 10px;">
            <textarea id="textarea-${postId}"
                      style="min-width: 100%;height: 40px;padding: 0;"
                      placeholder="Ваш комментарий..."
                      autocomplete="on" cols="1" spellcheck="true" oninput="expandTextarea(this);"></textarea></div>
        <div class="col d-lg-flex justify-content-lg-start align-items-lg-center justify-content-xl-start"
             style="max-width: 10%;padding-right: 0;padding-left: 0;margin-top: 17px;width: 37px;">
            <div id="send-button"><i class="fas fa-paper-plane send-comment" style="color: rgb(122,122,122);font-size: 20px;cursor: pointer;"
                onclick="sendComment('textarea-${postId}', ${postId})"></i></div>
        </div>
    </div>
</div>