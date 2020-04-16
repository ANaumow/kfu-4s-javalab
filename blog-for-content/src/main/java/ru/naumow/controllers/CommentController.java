package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.CommentForm;
import ru.naumow.entity.Comment;
import ru.naumow.entity.Post;
import ru.naumow.model.UserSessionData;
import ru.naumow.repositories.CommentRepository;
import ru.naumow.repositories.PostRepository;
import ru.naumow.repositories.UsersRepository;
import ru.naumow.services.CommentService;
import ru.naumow.services.PostService;

import java.util.List;

@Controller
public class CommentController {
    @Autowired CommentService commentService;
    @Autowired PostService    postService;

    @GetMapping("comments")// получить все комменты
    public ModelAndView getComments(
            @RequestParam("post_id") Long postId,
            @RequestParam(value = "w", required = false, defaultValue = "false") Boolean wait) {
        List<Comment> commentList = commentService.getCommentsByPostId(postId, wait);
        Post currentPost = postService.getById(postId);
        ModelAndView modelAndView = new ModelAndView("comment_section");
        modelAndView.addObject("commentList", commentList);
        modelAndView.addObject("post", currentPost);
        return modelAndView;
    }

    @PostMapping("comments")// отправить коммент
    @ResponseStatus(value = HttpStatus.OK) // пока не сообщаю если ошибка
    public void submitComment(CommentForm commentForm) {
        commentService.submitComment(commentForm);
    }

}
