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

<div class="d-lg-flex justify-content-lg-center" style="height: 100%;">
    <div class="row d-xl-flex justify-content-xl-center" style="z-index: 1;">
        <div class="col d-xl-flex justify-content-xl-center" style="width: 536px;">
            <!-- Start: Main-content -->
            <div data-aos="fade-down" data-aos-once="true" style="width: 490px;">
                <div style="margin-top: 10px;margin-bottom: 10px;">

                    <#include "posts.ftl">

                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
<script src="/resources/js/script.min.js"></script>
<script src="/resources/js/post.js"></script>
<script src="/resources/js/comments.js"></script>
<script src="/resources/js/comment_write.js"></script>
<script src="/resources/js/like.js"></script>
<script src="/resources/js/blog.js"></script>
</body>
</html>