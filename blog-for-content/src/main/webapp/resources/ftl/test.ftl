
<#import "/spring.ftl" as spring>
<#--<#include "./spring.ftl">-->

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    .error {
        color: #ff0000;
    }
</style>
<body>
<h1><@spring.message 'profile.test.welcome'/></h1>
<@spring.bind "testForm"/>
<form action="/test" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    Email: <br>
    <@spring.formInput "testForm.positive"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    Age: <br>
    <@spring.formInput "testForm.email"/>
    <@spring.showErrors "<br>","error"/>
    <input type="submit" value="Submit">
</form>

</body>
</html>