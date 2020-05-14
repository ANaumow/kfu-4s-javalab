package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.dto.LikeResponse;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.PostRepository;

import java.util.List;

@Component
public class LikeServiceImpl implements LikeService {

    @Autowired private PostRepository postRepository;

    @Override
    @Transactional
    public LikeResponse like(User user, Long postId) {
        boolean isLiked;
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found"));
        List<User> likes = post.getLikes();
        if (likes.contains(user)) {
            likes.remove(user);
            isLiked = false;
        } else {
            likes.add(user);
            isLiked = true;
        }
        postRepository.save(post);
        return LikeResponse.builder()
                .isLiked(isLiked)
                .likeCount(likes.size())
                .build();
    }
}
