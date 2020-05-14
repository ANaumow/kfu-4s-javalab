$(function () {
    let idStartWith = 'content-section-'
    $("div[id^=" + idStartWith + "]").each(function (i, div) {
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
})
