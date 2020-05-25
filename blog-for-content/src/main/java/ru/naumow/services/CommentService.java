package ru.naumow.services;

import ru.naumow.dto.CommentDto;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;

import java.util.List;

public interface CommentService {

    List<CommentDto> commentsByPost(Post post);

    List<CommentDto> commentsByPost(Post post, boolean doWait);

    CommentDto submitComment(User user, Post post, String text);

}
