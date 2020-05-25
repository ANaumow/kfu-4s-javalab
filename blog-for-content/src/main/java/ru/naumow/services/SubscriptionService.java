package ru.naumow.services;

import ru.naumow.dto.SubscriptionResponse;
import ru.naumow.entity.Blog;
import ru.naumow.entity.User;

public interface SubscriptionService {

    SubscriptionResponse toggleSubscription(User user, Blog blog);

}
