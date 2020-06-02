package ru.naumow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumow.dto.LikeResponse;
import ru.naumow.dto.SubscriptionResponse;
import ru.naumow.security.details.UserDetailsImpl;
import ru.naumow.services.BlogService;
import ru.naumow.services.PostService;

@RestController
@Profile("api")
public class PostApiController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/blog/{blog-alias}/posts/{post-id}/like")
    public ResponseEntity<?> like(
            @PathVariable("post-id") Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        LikeResponse likeResponse = postService.toggleLike(userDetails.getUser(), postId);
        return ResponseEntity.ok(likeResponse);
    }

}
