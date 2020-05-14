<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<form id="f" method="post">

    <#if blogDto??>
        <h1>Edit Blog</h1>
        <br>
        Alias <input type="name" name="alias" value="${blogDto.getAlias()}">
        <br>
        <br>
        Title <input type="text" name="title" value="${blogDto.getTitle()}">
        <br>
        <br>
        Subtitle <input type="text" name="subTitle" value="${blogDto.getSubTitle()}">
        <br>
        <br>
    <#--    Avatar <input type="file" name="avatar">-->

        Avatar <input type="file" id="file" name="file" placeholder="Имя файла..."/>
        <br>
        <br>
        BackgroundImage <input type="file" id="back-image" name="back-image" placeholder="Имя файла..."/>

    <#else>
        <h1>Create Blog</h1>
        <br>
        Alias <input type="name" name="alias" value="">
        <br>
        <br>
        Title <input type="text" name="title" value="">
        <br>
        <br>
        Subtitle <input type="text" name="subTitle" value="">
        <br>
        <br>
    <#--    Avatar <input type="file" name="avatar">-->

        Avatar <input type="file" id="file" name="file" placeholder="Имя файла..."/>
        <br>
        <br>
        BackgroundImage <input type="file" id="back-image" name="back-image" placeholder="Имя файла..."/>
    </#if>


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<button onclick="sendForm('/blog-create', 'f')" value="send">
    send
</button>

<script src="/resources/js/blog_creating.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</body>
</html>