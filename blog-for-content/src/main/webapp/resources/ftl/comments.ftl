<div id="comments-section-${postId}" class="borderer-up">
    <p class="lead" style="margin: 10px;font-size: 16px;">Комментарии</p>

    <#list commentList as comment>
        <#include "comment.ftl">
    </#list>


    <!-- Start: Comment-section -->
    <!-- End: Comment-section -->

    <!-- Start: Write-comment-section -->
    <#include "comment_write_section.ftl">
    <!-- End: Write-comment-section -->
</div>