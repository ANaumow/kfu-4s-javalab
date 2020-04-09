<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
</head>
<body>
<form method="post">

    <h1>${_csrf.token}</h1>
    <h1>Sing up</h1>
    <br>
    New email<input type="email" name="email">
    <br>
    New password<input type="password" name="password">
    <br>
    <input type="submit" value="go">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>