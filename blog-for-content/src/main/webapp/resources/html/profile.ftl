<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>

<div>
    <p>${user.name} ${user.surname}</p>
</div>
<div>
    <p>${user.vocation}</p>
</div>

<div>

    <#if user.blog??>
        <a href="./${user.blog.alias}">My Blog</a>
    <#else>
        <p>Create blog</p>
    </#if>

</div>

<div>
    <form action="/logout" method="post">
        <input type="submit" value="logout">
    </form>
</div>

<#--<div>
    <p>feed:</p>
    <div>
        <p>post</p>
    </div>
    <div>
        <p>post</p>
    </div>
</div>-->

</body>
</html>