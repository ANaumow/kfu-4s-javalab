package ru.naumow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.dto.LikeResponse;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.PostRepository;
import ru.naumow.services.LikeService;

import java.util.List;

@Component
public class LikeServiceImpl implements LikeService {

    @Override
    public LikeResponse toggleLike(User user, Post post) {
        boolean isLiked;
        List<User> likes = post.getLikes();
        if (likes.contains(user)) {
            likes.remove(user);
            isLiked = false;
        } else {
            likes.add(user);
            isLiked = true;
        }
        return LikeResponse.builder()
                .isLiked(isLiked)
                .likeCount(likes.size())
                .build();
    }

}
