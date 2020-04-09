package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.dto.ImageUploadResponse;
import ru.naumow.entity.FileInfo;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.entity.User;
import ru.naumow.model.UserSessionData;
import ru.naumow.services.editor.EditorService;
import ru.naumow.services.editor.FileService;

import java.util.UUID;

@Controller
public class EditorController {

    @Autowired
    private FileService fileService;

    @Autowired
    private EditorService editorService;

    @Autowired
    private UserSessionData userSessionData;

    @Autowired
    private StorageFilenameResolver storageFilenameResolver;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("post/editor")
    public ModelAndView get(@AuthenticationPrincipal(expression = "user") User user) {
        userSessionData.setBlogAlias(user.getBlog().getAlias());
        userSessionData.setUser(user);
        ModelAndView modelAndView = new ModelAndView("res/editormd/full");
        modelAndView.addObject("blog_alias", user.getBlog().getAlias());
        return modelAndView;
    }

    @PostMapping(value = "post/save-image", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ImageUploadResponse saveImage(@RequestParam("editormd-image-file") MultipartFile file) {
        try {
            FileInfo fileInfo = editorService.processImageSaving(file);//fileService.ssaveImage(file);
            String sharedUrl = storageFilenameResolver.asPostResource(fileInfo.getStorageFilename());
            return ImageUploadResponse.Ok(sharedUrl);
        } catch (Exception e) {
            return ImageUploadResponse.Error(e.getMessage());
        }
    }

    @PostMapping(value = "post/submit")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveMarkDown(@RequestParam("editormd_mark_down_text") String mdText) {
        editorService.submitPost(mdText);
        //editorService.processMdTextSaving(mdText);
    }

}
