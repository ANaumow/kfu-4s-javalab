package ru.naumow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.naumow.service.ContentService;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/api/content")
    public ResponseEntity<?> test() {
        contentService.save(1920, 1080, "img1");
        return ResponseEntity.ok().build();
    }


}
