package ru.naumow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumow.dto.PostDto;
import ru.naumow.security.details.UserDetailsImpl;
import ru.naumow.services.RecommendationService;

import java.util.List;

@RestController
@Profile("api")
public class RecommendationApiController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/api/posts/recommendations")
    public ResponseEntity<?> recommendations(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<PostDto> recommendations = recommendationService.recommendationsFor(userDetails.getUser());
        return ResponseEntity.ok(recommendations);
    }

}
