<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post">
    <h1>Sing in</h1>
    <br>
    Email<input type="email" name="email">
    <br>
    Password<input type="password" name="password">
    <br>
    <input type="checkbox" name="remember-me">Remember me
    <br>
    <input type="submit" value="go">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>