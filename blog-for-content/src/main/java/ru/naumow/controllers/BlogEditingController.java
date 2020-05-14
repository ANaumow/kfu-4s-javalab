package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.dto.BlogForm;
import ru.naumow.entity.FileInfo;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;
import ru.naumow.services.editor.EditorService;

@Controller
public class BlogEditingController {

    @Autowired private BlogService blogService;
    @Autowired private EditorService editorService;
    @Autowired private StorageFilenameResolver storageFilenameResolver;

    @GetMapping("/blog-create")
    public String getPage(
            @AuthenticationPrincipal(expression = "user") User user,
            Model model
    ) {
        if (blogService.ifUserHaveBlog(user)) {
            model.addAttribute(
                    "blogDto",
                    blogService.blogDtoForUser(blogService.blogAliasOf(user), user)
            );
        }
        return "blog_creating_page";
    }

    @PostMapping("/blog-create")
    public String createBlog(
            @RequestParam("back-image") MultipartFile backImage,
            @RequestParam("file") MultipartFile multipartFile,
            @AuthenticationPrincipal(expression = "user") User user,
            BlogForm blogForm
    ) {
        FileInfo fileInfo = editorService.processImageSaving(multipartFile);
        String sharedUrl = storageFilenameResolver.asPostResource(fileInfo.getStorageFilename());
        blogForm.setAvatarUrl(sharedUrl);

        fileInfo = editorService.processImageSaving(backImage);
        sharedUrl = storageFilenameResolver.asPostResource(fileInfo.getStorageFilename());
        blogForm.setBackgroundUrl(sharedUrl);

        blogService.createBlog(blogForm, user);
        return "redirect:/profile";
    }

}
