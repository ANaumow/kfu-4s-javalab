function sendComment(id, postId) {
    let text = $('textarea#' + id).val();
    /*alert(text);*/

    $.post("/comments", {
        text: text,
        postId: postId
    }).done(function ( ) {
        let counter = $("#comment-count-" + postId);
        let newCount = parseInt(counter.html()) + 1;
        counter.html(newCount);
    });
}