package ru.naumow.services;

import ru.naumow.dto.CommentDto;
import ru.naumow.dto.LikeResponse;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Content;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;

import java.util.List;

public interface PostService {

    List<PostDto> filteredPostsOfBlog(Long blogId, int level);

    List<PostDto> findAll();

    List<CommentDto> commentsByPost(Long postId, boolean doWait);

    CommentDto submitComment(User user, Long postId, String text);

    LikeResponse toggleLike(User user, Long postId);



}
