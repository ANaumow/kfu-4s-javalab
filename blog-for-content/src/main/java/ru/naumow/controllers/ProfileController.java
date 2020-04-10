package ru.naumow.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.entity.User;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @PreAuthorize("authentication.authenticated")
    @GetMapping
    public ModelAndView getProfilePage(@AuthenticationPrincipal(expression = "user") User user) {
        ModelAndView modelAndView = new ModelAndView("/resources/html/profile");
        modelAndView.addObject(user);
        return modelAndView;
    }

}
