<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All users</title>
</head>
<body>
<h1>All users</h1>
here all user go
<br>

<h1>${_csrf.token}</h1>
<form action="./logout" method="post">
    <input type="submit" value="logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>