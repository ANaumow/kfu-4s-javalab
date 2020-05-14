package ru.naumow.services;

import ru.naumow.dto.BlogDto;
import ru.naumow.dto.BlogForm;
import ru.naumow.dto.PostDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.User;

import java.util.List;

public interface BlogService {

    UserDto getAuthor(BlogDto blogDto);

    BlogDto getBlogByAlias(String blog);

    BlogDto blogDtoForUser(String alias, User user);

    String blogAliasOf(User user);

    boolean ifUserHaveBlog(User user);

    Blog createBlog(BlogForm blogForm, User user);

    boolean isOwner(String alias, User user);

    List<PostDto> getRecommendsFor(User user);

}
