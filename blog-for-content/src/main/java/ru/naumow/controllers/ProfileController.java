package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.annotation.CurrentUser;
import ru.naumow.dto.BlogInfo;
import ru.naumow.entity.User;
import ru.naumow.exceptions.BlogNotFoundException;
import ru.naumow.services.BlogService;
import ru.naumow.services.UserService;

@Controller
@Profile("mvc")
public class ProfileController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;


    @PreAuthorize("authentication.authenticated")
    @GetMapping("/profile")
    public String getProfilePage(@CurrentUser User user, Model model) {
        try {
            BlogInfo blogInfo = blogService.blogInfoByOwner(user);
            model.addAttribute("blogInfo", blogInfo);
            model.addAttribute("user", user);
        } catch (BlogNotFoundException e) {
            model.addAttribute("user", user);
        }
        return "profile";
    }

    @PostMapping("/profile/avatar")
    @ResponseStatus(HttpStatus.OK)
    public void saveAvatar(@CurrentUser User user, @RequestParam("avatar") MultipartFile file) {
        userService.setAvatar(file, user);
    }

}
