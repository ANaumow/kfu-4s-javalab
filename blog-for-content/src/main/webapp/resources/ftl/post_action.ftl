<#-- @ftlvariable name="userDto" type="ru.naumow.dto.UserDto" -->
<#-- @ftlvariable name="post" type="ru.naumow.dto.PostDto" -->
<div class="d-flex d-sm-flex d-md-flex justify-content-end align-items-center justify-content-sm-end align-items-sm-center justify-content-md-end align-items-md-center justify-content-xl-center borderer-up"
     id="action-section-1"
     style="margin-top: 10px;padding: 0 10px;height: 33px;">
    <div class="d-sm-flex d-md-flex d-lg-flex d-xl-flex align-items-sm-center align-items-md-center align-items-lg-center align-items-xl-center"
         style="width: 50%;height: 100%;">

        <p class="lead text-nowrap" style="font-size: 16px;margin: 0;">
            Опубликовано ${post.cratedAt}<#--Опубликовано 23 фев. 2019г. в 23:33--></p>
    </div>
    <div class="d-sm-flex d-md-flex d-lg-flex d-xl-flex justify-content-sm-end align-items-sm-center justify-content-md-end align-items-md-center justify-content-lg-end align-items-lg-center justify-content-xl-end align-items-xl-center"
         style="width: 50%;height: 100%;">
        <div id="comment-icon-${post.id}" style="cursor: pointer;"
             onclick="getComments('comments-${post.id}', ${post.id})">
            <i class="fas fa-comment-alt comment"
               style="font-size: 15px;color: rgb(132,132,132);font-weight: normal;font-style: normal;margin-right: 7px;margin-left: 7px;"></i>
        </div>
        <div class="text-nowrap d-xl-flex" style="width: 23px;">
            <p id="comment-count-${post.id}" class="text-nowrap" style="margin: 0;">${post.comments?size}</p>
        </div>
        <div style="cursor: pointer;"
             onclick="doLike('like-${post.id}', 'like-counter-${post.id}', ${post.id})">
            <i id="like-${post.id}" class="far fa-heart like"
               style="font-size: 15px;margin-right: 7px;margin-left: 7px;">

            </i>
        </div>
        <div class="text-nowrap" style="width: 23px;">
            <p id="like-counter-${post.id}" class="text-nowrap" style="margin: 0;">${post.likeCount}</p>
        </div>
    </div>
</div>
<script>
    $(function () {
        <#if post.likes?seq_contains(userDto)>
        let div = $("#like-${post.id}");
        div.addClass("liked");
        </#if>
    })
</script>
