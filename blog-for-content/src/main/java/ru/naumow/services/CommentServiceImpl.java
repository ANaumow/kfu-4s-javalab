package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.components.resolvers.CashedIdPool;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.CommentDto;
import ru.naumow.entity.Comment;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.CommentRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LocalDateTimeResolver timeResolver;

    @Autowired
    private CashedIdPool          cashedIdPool;

    @Override
    public List<CommentDto> commentsByPost(Post post) {
        List<Comment> comments = post.getComments();//postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found")).getComments();
        if (comments == null) {
            return Collections.emptyList();
        } else {
            return CommentDto.from(comments);
        }
    }

    @Override
    public List<CommentDto> commentsByPost(Post post, boolean doWait) {
        if (doWait) {
            Long postId = cashedIdPool.cashedOf(post.getId()); // получаю один и тот же объект для всех потоков
            synchronized (postId) {
                try {
                    postId.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return commentsByPost(post);
    }

    @Override
    @Transactional
    public CommentDto submitComment(User user, Post post, String text) {
        LocalDateTime now = timeResolver.now();
        Comment comment = Comment.builder()
                .user(user)
                .text(text)
                .cratedAt(now)
                .build();
        commentRepository.save(comment);
        post.getComments().add(comment);
        advertiseSubmitting(post.getId());
        return CommentDto.from(comment);
    }

    private void advertiseSubmitting(Long postId) {
        postId = cashedIdPool.cashedOf(postId);// получаю один и тот же объект для всех потоков
        synchronized (postId) {
            postId.notifyAll();
            cashedIdPool.dispose(postId); // удаляю объект из кэша
        }
    }



}
