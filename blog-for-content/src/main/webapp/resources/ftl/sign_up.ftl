<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="/resources/css/styles.min.css">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body style="background-color: rgb(244,242,239); text-align: center">

<#include "header_light.ftl">

<h3><#if err??>${err}</#if></h3>

<h1>Sing up</h1>
<@spring.bind "signUpForm"/>
<form id="d" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <br>
    Name
    <br>
    <@spring.formInput "signUpForm.name"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Surname
    <br>
    <@spring.formInput "signUpForm.surname"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Description
    <br>
    <@spring.formInput "signUpForm.vocation"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Email
    <br>
    <@spring.formInput "signUpForm.email"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Password
    <br>
    <@spring.formInput "signUpForm.password"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    <input type="submit" value="sign up!">
    <br>
    <a href="/sign-in">Login</a>

</form>

<#--<button onclick="console.log(($('form#d')))">
    sign up!
</button>-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resources/js/sign_up_validation.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
<script src="/resources/js/script.min.js"></script>

</body>
</html>
