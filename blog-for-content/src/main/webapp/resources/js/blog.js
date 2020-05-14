$(function () {
    $('#single-button-new-post').click(function () {
        window.location.href = '/editor?type=s';
    })

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        }
    });

    let startsWith = "single-button-sub-";
    $("div[id^=" + startsWith + "]").first().click(function () {
        let data = {
            blog_alias: $("div[id^=" + startsWith + "]").first().attr("id").substring(startsWith.length)
        }
        $.post("/sub", data).done(function () {
            alert("subed")
        })
    })
})