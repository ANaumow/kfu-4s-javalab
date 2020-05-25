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

    <#if blogInfo??>
        <h1>Edit Blog</h1>
        <br>
        Alias <input type="text" name="alias" value="${blogInfo.alias}">
        <br>
        <br>
        Title <input type="text" name="title" value="${blogInfo.title}">
        <br>
        <br>
        Subtitle <input type="text" name="subTitle" value="${blogInfo.subTitle}">
        <br>
        <br>
    <#--    Avatar <input type="file" name="avatar">-->

        Avatar <input type="file" id="avatar" name="avatar" placeholder="Имя файла..."/>
        <br>
        <br>
        BackgroundImage <input type="file" id="background" name="background" placeholder="Имя файла..."/>

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

        Avatar <input type="file" id="avatar" name="avatar" placeholder="Имя файла..."/>
        <br>
        <br>
        BackgroundImage <input type="file" id="background" name="background" placeholder="Имя файла..."/>
    </#if>


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<button onclick="sendForm('/blog-edit', 'f')" value="send">
    send
</button>

<script src="/resources/js/blog_creating.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</body>
</html>