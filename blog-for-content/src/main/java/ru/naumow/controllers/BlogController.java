package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.naumow.dto.BlogDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;
import ru.naumow.services.PostService;

@Controller
public class BlogController {
    @Autowired private BlogService blogService;
    @Autowired private PostService postService;


    @GetMapping("/{blog-alias}")
    public String get(
            @AuthenticationPrincipal(expression = "user") User user,
            @PathVariable("blog-alias") String alias,
            Model model
            //@CurrentBlog("blog-alias") BlogDto blogDto,
    ) {
        BlogDto blogDto = blogService.blogDtoForUser(alias, user);
        //BlogDto blogDto;
        //UserDto userDto;
        //userDto = blogService.getAuthor(blogDto);
        UserDto userDto = UserDto.from(user);

        //UserDto.from(Objects.requireNonNull(usersRepository.findByEmail(user.getEmail()).orElse(null)));

        //blogDto = blogService.getBlogByAlias(blogAlias);

        /*List<PostDto> postList = PostDto.from(postService.getAllByBlogId(blogDto.getId()));
        postList.forEach(postDto -> {
            Long likeCount = likeRepository.countByIdPostId(postDto.getId());
            postDto.setLikes(likeCount);
        });*/

        //model.addAttribute("postList", postList);
        boolean isOwner = blogService.isOwner(alias, user);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("blogDto", blogDto);
        //model.addAttribute("userDto", userDto);
        String aliasOf = blogService.blogAliasOf(user);
        model.addAttribute("userBlogAlias", aliasOf);
        model.addAttribute("userDto", userDto);
        return "blog";
    }


}
