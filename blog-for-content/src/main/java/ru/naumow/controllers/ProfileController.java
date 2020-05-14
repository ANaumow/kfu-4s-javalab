package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.entity.FileInfo;
import ru.naumow.entity.User;
import ru.naumow.model.UserSessionData;
import ru.naumow.repositories.UsersRepository;
import ru.naumow.services.BlogService;
import ru.naumow.services.editor.EditorService;

import javax.annotation.Resource;

@Controller
public class ProfileController {
/*
    @Autowired
    private UserSessionData userSessionData;*/

    @Autowired
    UserSessionData userSessionData;

    @Autowired private StorageFilenameResolver storageFilenameResolver;

    @Autowired private BlogService blogService;

    @Autowired private UsersRepository usersRepository;

    @Autowired
    private EditorService editorService;

    @PreAuthorize("authentication.authenticated")
    @GetMapping("/profile")
    public String getProfilePage(
            @AuthenticationPrincipal(expression = "user") User user,
            Model model
    ) {
        System.out.println(userSessionData.getUser()/*.getId()*/);
        if (blogService.ifUserHaveBlog(user)) {
            String blogAlias = blogService.blogAliasOf(user);
            userSessionData.setBlogAlias(blogAlias);
            model.addAttribute("blogAlias", blogAlias);
        }
        userSessionData.setUser(user);
        model.addAttribute("user", user);
        System.out.println(userSessionData.getUser());
        return "profile";
    }

    @PostMapping("/profile/avatar")
    @ResponseStatus(HttpStatus.OK)
    public void saveAvatar(
            @RequestParam("avatar") MultipartFile file,
            @AuthenticationPrincipal(expression = "user") User user
    ) {
        FileInfo fileInfo = editorService.processImageSaving(file);
        String sharedUrl = storageFilenameResolver.asPostResource(fileInfo.getStorageFilename());
        user.setAvatarUrl(sharedUrl);
        usersRepository.save(user);
    }

}
