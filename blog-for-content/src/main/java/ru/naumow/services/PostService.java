package ru.naumow.services;

import ru.naumow.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getByBlogId(Long blogId);

}
