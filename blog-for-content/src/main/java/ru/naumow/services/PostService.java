package ru.naumow.services;

import ru.naumow.dto.PostDto;
import ru.naumow.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllByBlogId(Long blogId);

    Post getById(Long id);

    void waitForNew(Long id);

    List<PostDto> postsOf();

    List<PostDto> postsOfLevel(Long blogId, int level);



}
