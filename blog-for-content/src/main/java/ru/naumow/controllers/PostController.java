package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.naumow.annotation.CurrentUser;
import ru.naumow.annotation.MyAnno;
import ru.naumow.dto.CommentDto;
import ru.naumow.dto.LikeResponse;
import ru.naumow.entity.User;
import ru.naumow.services.PostService;

import java.util.List;

@Controller
@Profile("mvc")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/like", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @MyAnno
    public LikeResponse like(@RequestParam Long postId, @CurrentUser User user) {
        return postService.toggleLike(user, postId);
    }

    @GetMapping("/comments")
    public String getComments(
            @RequestParam(value = "w", required = false, defaultValue = "false") boolean wait,
            @RequestParam("post_id") Long postId,
            Model model
    ) {
        List<CommentDto> commentList = postService.commentsByPost(postId, wait);
        model.addAttribute("commentList", commentList);
        model.addAttribute("postId", postId);
        return "comments";
    }

    @PostMapping("/comments")
    public ResponseEntity<?> submitComment(@CurrentUser User user, Long postId, String text) {
        try {
            postService.submitComment(user, postId, text);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
