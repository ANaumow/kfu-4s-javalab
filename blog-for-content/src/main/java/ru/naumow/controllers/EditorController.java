package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.annotation.CurrentUser;
import ru.naumow.dto.FileDto;
import ru.naumow.dto.ImageUploadResponse;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.User;
import ru.naumow.services.EditorService;

@Controller
@Profile("mvc")
public class EditorController {

    @Autowired
    private EditorService editorService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editor")
    public String get(
            @RequestParam(value = "postId", required = false) Long postId,
            @RequestParam(value = "type", required = false) String type,
            @CurrentUser User user,
            Model model
    ) {
        model.addAttribute("postId", postId);
        model.addAttribute("type", type);
        model.addAttribute("success_href", editorService.makeSuccessfulUrl(user));
        return "editor";
    }

    @PostMapping(value = "/editor/save-image", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ImageUploadResponse saveImage(@RequestParam("editormd_image_file") MultipartFile image) {
        try {
            FileDto fileDto = editorService.processImageSaving(image);
            return ImageUploadResponse.Ok(fileDto.getPublicUrl());
        } catch (Exception e) {
            return ImageUploadResponse.Error(e.getMessage());
        }
    }

    @PostMapping("/editor/submit")
    public ResponseEntity<?> saveMarkDown(
            @RequestParam(value = "postId", required = false) Long postId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam("editormd_mark_down_text") String mdText,
            @CurrentUser User user
    ) {
        try {
            PostDto postDto = editorService.submitPost(user, mdText, postId, type);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
