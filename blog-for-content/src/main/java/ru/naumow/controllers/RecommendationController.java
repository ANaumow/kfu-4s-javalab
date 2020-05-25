package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.naumow.annotation.CurrentUser;
import ru.naumow.dto.BlogInfo;
import ru.naumow.dto.PostDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;
import ru.naumow.services.RecommendationService;

import java.util.List;

@Controller
@Profile("mvc")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/recommendations")
    public String getPage(@CurrentUser User user, Model model) {
        UserDto userDto = UserDto.from(user);
        List<PostDto> postDtoList = recommendationService.recommendationsFor(user);
        BlogInfo userBlogInfo = blogService.blogInfoByOwner(user);
        model.addAttribute("user", userDto);
        model.addAttribute("userBlogInfo", userBlogInfo);
        model.addAttribute("posts", postDtoList);
        return "recommendations";
    }

}
