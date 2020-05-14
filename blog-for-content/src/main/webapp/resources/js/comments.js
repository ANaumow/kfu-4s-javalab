/*$(function () {
    let idStartWith = 'comment-icon-'
    $("div[id^=" + idStartWith + "]").each(function (i, div) {
        let id = $(div).attr("id");
        let postId = id.substring(idStartWith.length);
        $(div).attr("onclick", "console.log(\"dfadf\"); getComments(\"" + id + "\", \"" + postId + "\");")
    })
})*/

function getComments(divId, postId, wait) {
    let div = $("#" + divId);

    if (wait == null && div.hasClass("loaded")) {
        if (div.is(":hidden")) {
            div.show();
        } else {
            div.hide();
        }
    }

    if (wait != null || !div.hasClass("loaded") && wait == null) {
        console.log("i am here");
        div.addClass("loaded");
        let data;
        if (wait != null) {
            data = {
                post_id: postId,
                w: 1
            }
        } else {
            data = {
                post_id: postId
            }
        }
        $.get("comments", data, function (response) {
            div.html(response);
            getComments(divId, postId, 1);
        })
    }

}