package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.components.resolvers.CashedIdPool;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.CommentForm;
import ru.naumow.entity.Comment;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.model.UserSessionData;
import ru.naumow.repositories.CommentRepository;
import ru.naumow.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private PostRepository    postRepository;
    @Autowired private UserSessionData   userSessionData;

    @Autowired private LocalDateTimeResolver timeResolver;
    @Autowired private CashedIdPool          cashedIdPool;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        return commentList == null ? Collections.emptyList() : commentList;
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId, boolean wait) {
        if (wait) {
            postId = cashedIdPool.cashedOf(postId); // получаю один и тот же объект для всех потоков
            synchronized (postId) {
                try {
                    postId.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return getCommentsByPostId(postId);
    }

    @Override
    public void submitComment(CommentForm commentForm) {
        Post post = postRepository.findById(commentForm.getPostId())
                .orElseThrow(IllegalStateException::new);
        User user = userSessionData.getUser();
        String text = commentForm.getText();
        LocalDateTime now = timeResolver.now();

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .text(text)
                .cratedAt(now)
                .build();
        commentRepository.save(comment);
        advertiseSubmitting(commentForm.getPostId());
    }

    private void advertiseSubmitting(Long postId) {
        postId = cashedIdPool.cashedOf(postId);// получаю один и тот же объект для всех потоков
        synchronized (postId) {
            postId.notifyAll();
            cashedIdPool.dispose(postId); // удаляю объект из кэша
        }
    }

}
