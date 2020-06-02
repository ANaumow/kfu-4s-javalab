package ru.naumow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.naumow.annotation.CurrentUser;
import ru.naumow.dto.BlogDto;
import ru.naumow.dto.PostDto;
import ru.naumow.dto.SubscriptionResponse;
import ru.naumow.security.details.UserDetailsImpl;
import ru.naumow.services.BlogService;

import java.util.List;

@RestController
@Profile("api")
public class BlogApiController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/api/blog/{blog-alias}/posts")
    public ResponseEntity<List<PostDto>> getPosts(
            @PathVariable("blog-alias") String blogAlias,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        BlogDto preparedBlog = blogService.blogForUserView(blogAlias, userDetails.getUser());
        return ResponseEntity.ok(preparedBlog.getPosts());
    }

    @PostMapping("/api/blog/{blog-alias}/sub")
    public ResponseEntity<?> sub(
            @PathVariable("blog-alias") String alias,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        SubscriptionResponse response = blogService.subscribe(userDetails.getUser(), alias);
        return ResponseEntity.ok(response);
    }

}
