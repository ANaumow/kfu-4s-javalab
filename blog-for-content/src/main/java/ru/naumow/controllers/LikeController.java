package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.naumow.dto.LikeResponse;
import ru.naumow.entity.User;
import ru.naumow.services.LikeService;

@Controller
public class LikeController {

    @Autowired private LikeService likeService;

    @PostMapping(value = "/like", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LikeResponse like(
            @RequestParam Long postId,
            @AuthenticationPrincipal(expression = "user") User user
    ) {
        return likeService.like(user, postId);
    }

}
