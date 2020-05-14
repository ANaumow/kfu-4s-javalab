<#-- @ftlvariable name="comment" type="ru.naumow.dto.CommentDto" -->
<div id="post-comment-${comment.id}" style="margin-top: 3px;margin-bottom: 3px;">
    <div class="row" style="margin-left: 0;margin-right: 0;">
        <div class="col d-sm-flex d-lg-flex justify-content-sm-end align-items-sm-start justify-content-lg-end align-items-lg-start" style="max-width: 20%;padding: 10px;">
            <div class="border rounded-circle" style="background-image: url('/resources/img/avatar.jpg');background-color: rgba(150,106,39,0.6);background-size: cover;background-position: bottom;background-repeat: no-repeat;height: 40px;width: 40px;cursor: pointer;"></div>
        </div>
        <div class="col align-self-start" style="max-width: 80%;padding: 10px;">
            <p class="text-left user" style="font-size: 15px;filter: blur(0px);font-style: normal;font-weight: bold;margin: 0;cursor: pointer;">${comment.user.name} ${comment.user.surname}<#--Pavel Lomow--></p>
            <p class="text-break text-left" style="font-size: 14px;margin: 0;">${comment.text}<#--nice!--></p>
        </div>
    </div>
</div>