package ru.naumow.services;

import ru.naumow.dto.*;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;

public interface BlogService {

    BlogDto blogForUserView(String alias, User user);

    BlogDto createBlog(BlogForm blogForm, User user);

    BlogInfo blogInfoByOwner(User user);

    LikeResponse like(User user, Long postId);

    SubscriptionResponse subscribe(User user, String alias);

}
