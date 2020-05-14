<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>

<div>
    <p>${user.name} ${user.surname}</p>
</div>
<div>
    <p>${user.vocation}</p>
</div>

<div>

    <#if blogAlias??>
        <a href="./${blogAlias}">My Blog</a>
    <#else>
        <a href="/blog-create">Create blog</a>
    </#if>

</div>

<div>
    <form action="/logout" method="post">
        <input type="submit" value="logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script>
    function sendFile() {
        // данные для отправки
        let formData = new FormData();
        // забрал файл из input
        let files = ($('#file'))[0]['files'];
        // добавляю файл в formData
        [].forEach.call(files, function (file, i, files) {
            console.log(file);
            formData.append("avatar", file);
        });

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajaxSetup({
            beforeSend: function( xhr ) {
                xhr.setRequestHeader(header, token);
            }
        });

        $.ajax({
            type: "POST",
            url: "/profile/avatar",
            data: formData,
            processData: false,
            contentType: false
        }).done(function (response) {
            alert(response)
        }).fail(function () {
            alert('Error')
        });
    }
</script>

<div>
    <input type="file" id="file" name="file" placeholder="Имя файла..."/>
    <button onclick="sendFile()">
        Загрузить файл
    </button>
</div>



<#--<div>
    <p>feed:</p>
    <div>
        <p>post</p>
    </div>
    <div>
        <p>post</p>
    </div>
</div>-->

</body>
</html>