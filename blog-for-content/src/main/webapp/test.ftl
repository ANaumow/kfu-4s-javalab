
<#import "/spring.ftl" as spring>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<@spring.bind "testForm"/>
<form action="/profile" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    Email: <br>
    <@spring.formInput "testForm.positive"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    Age: <br>
    <@spring.formInput "testForm.negative"/>
    <@spring.showErrors "<br>"/>
    <input type="submit" value="Submit">
</form>

</body>
</html>