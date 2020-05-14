package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.dto.ImageUploadResponse;
import ru.naumow.entity.FileInfo;
import ru.naumow.entity.User;
import ru.naumow.model.UserSessionData;
import ru.naumow.services.BlogService;
import ru.naumow.services.editor.EditorService;

@Controller
@RequestMapping("/editor")
public class EditorController {
    @Autowired private EditorService           editorService;
    @Autowired private StorageFilenameResolver storageFilenameResolver;

    @Autowired private BlogService blogService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String get(
            @AuthenticationPrincipal(expression = "user") User user,
            @RequestParam(value = "edit-post", required = false) String name,
            @RequestParam("type") String type,
            Model model
    ) {
        String blogAlias = blogService.blogAliasOf(user);
        boolean isOwner = blogService.isOwner(blogAlias, user);
        model.addAttribute("blog_alias", blogAlias);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("type", type);
        return "editor";
    }

    @PostMapping(value = "/save-image", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ImageUploadResponse saveImage(@RequestParam("editormd_image_file") MultipartFile file) {
        try {
            //TODO make using FileDto
            FileInfo fileInfo = editorService.processImageSaving(file);
            String sharedUrl = storageFilenameResolver.asPostResource(fileInfo.getStorageFilename());
            return ImageUploadResponse.Ok(sharedUrl);
        } catch (Exception e) {
            return ImageUploadResponse.Error(e.getMessage());
        }
    }

    @PostMapping(value = "/submit")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveMarkDown(
            @AuthenticationPrincipal(expression = "user") User user,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam("editormd_mark_down_text") String mdText,
            @RequestParam("blogAlias") String blogAlias
    ) {
        editorService.submitPost(mdText, blogAlias, user, type);
    }

}
