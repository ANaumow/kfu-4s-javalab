package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.dto.BlogDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;
import ru.naumow.services.PostService;

import java.util.List;

@Controller
@RequestMapping("{blog-alias}")
public class BlogController {
    @Autowired private BlogService blogService;
    @Autowired private PostService postService;

    @GetMapping
    public ModelAndView get(@AuthenticationPrincipal(expression = "user") User user,
            @PathVariable("blog-alias") String alias) {

        BlogDto blogDto;
        UserDto userDto;
        //UserDto currentUserDto;

        ModelAndView modelAndView;

        if (alias.equals(user.getBlog().getAlias())){
            userDto = UserDto.from(user);
            blogDto = BlogDto.from(user.getBlog());
            //currentUserDto = userDto;
            modelAndView = new ModelAndView("my_blog");
        } else {
            blogDto = blogService.getBlogByAlias(alias);
            userDto = blogService.getAuthor(blogDto);
            //currentUserDto = UserDto.from(user);
            modelAndView = new ModelAndView("guest_blog");
        }

        List<Post> postList = postService.getAllByBlogId(blogDto.getId());

        modelAndView.addObject("postList", postList);
        modelAndView.addObject("blogDto", blogDto);
        modelAndView.addObject("userDto", userDto);
        //modelAndView.addObject("currentUserDto", currentUserDto);
        return modelAndView;
    }

}
