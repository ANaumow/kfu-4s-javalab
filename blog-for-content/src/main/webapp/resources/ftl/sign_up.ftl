<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<form id="d" method="post">

    <h1>Sing up</h1>
    <br>
    Name <input type="name" name="name">
    <br>
    <br>
    Surname <input type="text" name="surname">
    <br>
    <br>
    Description <input type="text" name="vocation">
    <br>
    <br>
    Email <input type="email" name="email">
    <br>
    <br>
    Password <input type="password" name="password">
    <br>
    <br>
    Blog alias <input type="text" name="blogAlias">
    <br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="sign up">


</form>

<#--<button onclick="console.log(($('form#d')))">
    sign up!
</button>-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


</body>
</html>