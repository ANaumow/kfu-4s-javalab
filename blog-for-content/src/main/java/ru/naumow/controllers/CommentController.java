package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.naumow.dto.CommentForm;
import ru.naumow.entity.Comment;
import ru.naumow.entity.Post;
import ru.naumow.services.CommentService;
import ru.naumow.services.PostService;

import java.util.List;

@Controller
public class CommentController {
    @Autowired CommentService commentService;
    @Autowired PostService    postService;

    @GetMapping("comments")// получить все комменты
    public String getComments(
            @RequestParam(value = "w", required = false, defaultValue = "false") Boolean wait,
            @RequestParam("post_id") Long postId,
            Model model
    ) {
        List<Comment> commentList = commentService.getCommentsByPostId(postId, wait);

        model.addAttribute("commentList", commentList);
        model.addAttribute("postId", postId);
        return "comments";
    }

    @PostMapping("comments")// отправить коммент
    @ResponseStatus(value = HttpStatus.OK) // пока не сообщаю если ошибка
    public void submitComment(CommentForm commentForm) {
        commentService.submitComment(commentForm);
    }

}
