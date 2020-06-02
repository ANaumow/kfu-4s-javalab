<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="/resources/css/styles.min.css">
</head>
<body style="background-color: rgb(244,242,239); ">


<#include "header_light.ftl">

<div style="text-align: center">

    <form method="post">
        <h1>Sing in</h1>
        <br>
        Email
        <br>
        <input type="email" name="email">
        <br>
        Password
        <br>
        <input type="password" name="password">
        <br>
        <input type="checkbox" name="remember-me">Remember me
        <br>
        <input type="submit" value="go">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <br>
        <br>
        <a href="/sign-up">Registration</a>

    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
<script src="/resources/js/script.min.js"></script>
</body>
</html>