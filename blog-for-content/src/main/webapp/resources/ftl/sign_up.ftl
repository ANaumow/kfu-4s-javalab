<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

<h3><#if err??>${err}</#if></h3>

<h1>Sing up</h1>
<@spring.bind "signUpForm"/>
<form id="d" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <br>
    Name
    <@spring.formInput "signUpForm.name"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Surname
    <@spring.formInput "signUpForm.surname"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Description
    <@spring.formInput "signUpForm.vocation"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Email
    <@spring.formInput "signUpForm.email"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    Password
    <@spring.formInput "signUpForm.password"/>
    <@spring.showErrors "<br>"/>
    <br>
    <br>
    <input type="button" onclick="trySubmit()" value="js-validation">
    <input type="submit" value="without-js-validation">

<#--    <button value="sign up" onclick="trySubmit()"></button>-->

</form>

<#--<button onclick="console.log(($('form#d')))">
    sign up!
</button>-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resources/js/sign_up_validation.js"></script>

</body>
</html>