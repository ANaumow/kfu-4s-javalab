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



    var stompClient;
    connect();


})


function connect() {

    let blogAlias = window.location.href.substring(window.location.href.lastIndexOf('/') + 1)
    console.log("blogAlias: " + blogAlias)
    let socket = new SockJS('/blog-for-content');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/blog/update/' + blogAlias, function (message) {
            console.log("stomp subed")

            let html = JSON.parse(message.body)['buffer'];
            let idStartWith = 'content-section-'
            console.log(message.body.buffer)


            $(html).find("div[id^=" + idStartWith + "]").each(function (i, div) {
                let url = $(div).attr("content-url");
                $.get(url, function (md) {
                    console.log(md)
                    //$(div).html(md)
                    let id = $(div).attr("id");
                    // это кароче использование стороней библиотеки для постов моего блога
                    // оно не играет никакой роли для лонг полинга
                    editormd.urls.atLinkBase = "/";
                    editormd.markdownToHTML(id, {
                        markdown: md,
                        htmlDecode: "style,script,iframe",
                        tocm: true,
                        emoji: true,
                        taskList: true,
                        tex: true,
                        flowChart: true,
                        sequenceDiagram: true,
                    });
                });

            })

            $("#posts-section").prepend(html)

        });
    });
}