package ru.naumow.services;

import ru.naumow.dto.LikeResponse;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;

public interface LikeService {

    LikeResponse toggleLike(User user, Post post);

}
