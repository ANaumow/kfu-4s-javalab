package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.dto.BlogDto;
import ru.naumow.dto.UserDto;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.UsersRepository;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired public BlogRepository  blogRepository;
    @Autowired public UsersRepository usersRepository;

    @Override
    public UserDto getAuthor(BlogDto blogDto) {
        return UserDto.from(usersRepository.getUserByBlogAlias(blogDto.getAlias())
                .orElseThrow(IllegalStateException::new));
    }

    @Override
    public BlogDto getBlogByAlias(String alias) {
        return BlogDto.from(blogRepository.findByAlias(alias)
                .orElseThrow(IllegalStateException::new));
    }
}
