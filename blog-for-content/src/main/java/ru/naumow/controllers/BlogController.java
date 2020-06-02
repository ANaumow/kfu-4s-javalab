package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.naumow.exceptions.BlogNotFoundException;
import ru.naumow.annotation.CurrentUser;
import ru.naumow.dto.BlogDto;
import ru.naumow.dto.BlogForm;
import ru.naumow.dto.BlogInfo;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;

@Controller
@Profile("mvc")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/{blog-alias}")
    public String get(@CurrentUser User user, @PathVariable("blog-alias") String blogAlias, Model model) {
        BlogDto preparedBlog = blogService.blogForUserView(blogAlias, user);
        UserDto userDto = UserDto.from(user);
        model.addAttribute("currentBlog", preparedBlog);
        model.addAttribute("user", userDto);
        try {
            BlogInfo userBlogInfo = blogService.blogInfoByOwner(user);
            model.addAttribute("userBlogInfo", userBlogInfo);
        } catch (BlogNotFoundException ignored) {
        }
        return "blog";
    }

    @GetMapping("/blog-edit")
    public String getPage(@CurrentUser User user, Model model) {
        try {
            BlogInfo blogInfo = blogService.blogInfoByOwner(user);
            model.addAttribute("blogInfo", blogInfo);
        } catch (BlogNotFoundException e) {
            return "blog_creating_page";
        }
        return "blog_creating_page";
    }

    @PostMapping("/blog-edit")
    public String createBlog(@CurrentUser User user, BlogForm blogForm) {
        blogService.createBlog(blogForm, user);
        return "redirect:/profile";
    }

    @PostMapping("/sub")
    public ResponseEntity<?> sub(@CurrentUser User user, @RequestParam("blog_alias") String blogAlias) {
        try {
            blogService.subscribe(user, blogAlias);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
