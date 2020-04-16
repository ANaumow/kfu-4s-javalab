package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.entity.User;
import ru.naumow.model.UserSessionData;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserSessionData userSessionData;

    @PreAuthorize("authentication.authenticated")
    @GetMapping
    public ModelAndView getProfilePage(@AuthenticationPrincipal(expression = "user") User user) {
        userSessionData.setBlogAlias(user.getBlog().getAlias());
        userSessionData.setUser(user);

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject(user);
        return modelAndView;
    }

}
