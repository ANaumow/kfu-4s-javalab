<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
</head>
<body>
<form method="post">


    <h1>Sing in</h1>
<#--    <h1>${_csrf.token}</h1>-->
    <br>
    Email<input type="email" name="email">
    <br>
    Password<input type="password" name="password">
    <br>
    <input type="checkbox" name="remember-me">Remember me
    <br>
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->

    <input type="submit" value="go">
</form>
</body>
</html>