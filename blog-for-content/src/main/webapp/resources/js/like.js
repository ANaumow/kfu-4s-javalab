function doLike(likeId, counterId, postId) {

    let data = {
        postId: postId
    }

    $.post("/like", data, function (response) {
        let like = $('#' + likeId);
        console.log(response)
        if (response.liked) {
            like.addClass("liked");
        } else {
            like.removeClass("liked");
        }
        let counter = $('#' + counterId);
        let newCount = parseInt(counter.html()) + 1;
        console.log(newCount);
        counter.html(response.likeCount);
    });

}