<#-- @ftlvariable name="post" type="ru.naumow.dto.PostDto" -->
<div id="post-holder-${post.id}" class="custom-post-section"
     style="margin-top: 15px;background-color: #ffffff;margin-bottom: 15px;padding-bottom: 5px;">
    <!-- Start: Content-section -->

    <div style="padding-top: 10px">
        <div id="content-section-${post.id}" content-url="${post.contentUrl}">

        </div>
    </div>

    <!-- End: Content-section -->
    <#include "post_action.ftl">

    <div id="comments-${post.id}">

    </div>
    <!-- Start: Comments-section -->
    <#--<#include "comments.ftl">-->
    <!-- End: Comments-section -->
</div>