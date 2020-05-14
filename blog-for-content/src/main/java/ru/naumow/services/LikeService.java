package ru.naumow.services;

import ru.naumow.dto.LikeResponse;
import ru.naumow.entity.User;

public interface LikeService {

    LikeResponse like(User user, Long postId);

}
