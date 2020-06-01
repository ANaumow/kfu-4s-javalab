package ru.naumow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.naumow.service.ContentService;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("api/contents")
    public ResponseEntity<?> create(
            @RequestParam("body") String body,
            @RequestParam("type") String type,
            @RequestParam("channel-id") Long id
    ) {
        contentService.create(body, type, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("api/videos")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(contentService.videos());
    }

    @GetMapping("api/stats")
    public ResponseEntity<?> stats() {
        return ResponseEntity.ok().body(contentService.stats());
    }

}
