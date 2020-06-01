package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumow.dto.StatisticDto;
import ru.naumow.dto.UserDto;
import ru.naumow.service.StorageService;

@Controller
public class ContentController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/api/users/{userId}/contents")
    public ResponseEntity<?> createContent(
            @RequestParam("type") String type,
            @RequestParam("body") String body,
            @PathVariable("userId") Long userId
    ) {
        storageService.createContent(type, body, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable("userId") Long userId
    ) {
        UserDto userDto = storageService.showUser(userId);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/api/users/")
    public ResponseEntity<UserDto> createUser() {
        UserDto created = storageService.createUser();
        return ResponseEntity.ok(created);
    }

    @GetMapping("/api/stats/{userId}")
    public ResponseEntity<StatisticDto> statsForUser(@PathVariable("userId") Long userId) {
        StatisticDto statsForUser = storageService.statsForUser(userId);
        return ResponseEntity.ok(statsForUser);
    }

}
