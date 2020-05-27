<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>фвыафыва</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="/resources/css/styles.min.css">
    <link rel="stylesheet" href="/editor/css/editormd.preview.css"/>
</head>

<body style="background-color: rgb(244,242,239);">

<script src="/editor/js/jquery.min.js"></script>
<script src="/editor/lib/marked.min.js"></script>
<script src="/editor/lib/prettify.min.js"></script>

<script src="/editor/lib/raphael.min.js"></script>
<script src="/editor/lib/underscore.min.js"></script>
<script src="/editor/lib/sequence-diagram.min.js"></script>
<script src="/editor/lib/flowchart.min.js"></script>
<script src="/editor/lib/jquery.flowchart.min.js"></script>

<script src="/editor/editormd.js"></script>

<#include "header.ftl">
<!-- Start: Header -->
<#include "blog_background.ftl">
<!-- End: Header -->
<div class="d-lg-flex justify-content-lg-center" style="height: 100%;">
    <div class="row d-xl-flex justify-content-xl-center" style="z-index: 1;">
        <#list currentBlog.posts as post>
            <#if post.type = "l">
                <div class="col custom-post-section"
                     style="position: sticky;top: 54px;overflow-y: scroll;height: calc(100vh - 68px);background-color: #ffffff;z-index: 5;padding: 19px;width: 244px;margin: 14px;">
                    <div id="content-section-${post.id}" content-url="${post.contentUrl}">
                    </div>
                    <#--            <#include "content.ftl">-->
                </div>
                <#break>
            </#if>
        </#list>
        <div class="col d-xl-flex justify-content-xl-center" style="margin-top: -73px;width: 536px;">
            <!-- Start: Main-content -->
            <div data-aos="fade-down" data-aos-once="true" style="width: 490px;">
                <!-- Start: Blog-info -->
                <#include "blog_info.ftl">
                <!-- End: Blog-info -->
                <!-- Start: Feed -->
                <div style="margin-top: 10px;margin-bottom: 10px;">
                    <!-- Start: subscribe-button -->
                    <#if currentBlog.owner.id != user.id>
                        <#assign buttonText="Подписаться">
                        <#assign name="single-button-sub-${currentBlog.info.alias}">
                        <#include "post_button_single.ftl">
                    <#else>
                        <#assign buttonText="Новый пост">
                        <#assign name="single-button-new-post">
                        <#include "post_button_single.ftl">
                    </#if>
                    <!-- End: subscribe-button -->
                    <!-- Start: create_post -->
                    <!-- End: create_post -->
                    <!-- Start: Posts-holder -->
                    <#assign posts=currentBlog.posts>
                    <#include "posts.ftl">
                    <!-- End: Posts-holder -->
                </div>
                <!-- End: Feed -->
            </div>
            <!-- End: Main-content -->
        </div>


        <#list currentBlog.posts as post>
            <#if post.type = "r">
                <div class="col custom-post-section"
                     style="position: sticky;top: 54px;overflow-y: scroll;height: calc(100vh - 68px);background-color: #ffffff;z-index: 5;padding: 19px;width: 244px;margin: 14px;">
                    <div id="content-section-${post.id}" content-url="${post.contentUrl}">
                    </div>
                </div>
                <#break>
            </#if>
        </#list>
        <#--<#include "content.ftl">-->
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/resources/js/script.min.js"></script>
<script src="/resources/js/post.js"></script>
<script src="/resources/js/comments.js"></script>
<script src="/resources/js/comment_write.js"></script>
<script src="/resources/js/like.js"></script>
<script src="/resources/js/blog.js"></script>
</body>

</html>