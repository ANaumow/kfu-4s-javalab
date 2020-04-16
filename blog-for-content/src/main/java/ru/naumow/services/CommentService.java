package ru.naumow.services;

import ru.naumow.dto.CommentForm;
import ru.naumow.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByPostId(Long postId);

    List<Comment> getCommentsByPostId(Long postId, boolean wait);

    void submitComment(CommentForm commentForm);
}
