package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.naumow.entity.User;
import ru.naumow.services.SubService;

@Controller
public class SubController {

    @Autowired private SubService subService;

    @PostMapping("/sub")
    @ResponseStatus(HttpStatus.OK)
    public void sub(
            @AuthenticationPrincipal(expression = "user") User user,
            @RequestParam("blog_alias") String blogAlias
    ) {
        subService.sub(user, blogAlias);
    }
}
