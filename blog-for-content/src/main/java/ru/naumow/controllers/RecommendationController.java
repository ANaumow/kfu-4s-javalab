package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.naumow.dto.PostDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;

import java.util.List;

@Controller
public class RecommendationController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/recommendations")
    public String getPage(
            @AuthenticationPrincipal(expression = "user") User user,
            Model model
    ) {
        UserDto userDto = UserDto.from(user);
        String aliasOf = blogService.blogAliasOf(user);
        List<PostDto> postDtoList = blogService.getRecommendsFor(user);
        model.addAttribute("userDto", userDto);
        model.addAttribute("userBlogAlias", aliasOf);
        model.addAttribute("posts", postDtoList);
        return "recommendations";
    }

}
