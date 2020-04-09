package ru.naumow.services;

import ru.naumow.dto.BlogDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.Blog;

public interface BlogService {

    UserDto getAuthor(BlogDto blogDto);

    BlogDto getBlogByAlias(String blog);

}
