package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.SubscriptionId;
import ru.naumow.entity.User;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.SubscriptionRepository;

@Component
public class SubServiceImpl implements SubService {

    @Autowired private SubscriptionRepository subscriptionRepository;
    @Autowired private BlogRepository blogRepository;

    @Override
    public void sub(User user, String alias) {
        Blog blog = blogRepository.findByAlias(alias).orElseThrow(() -> new IllegalArgumentException("not found"));
        SubscriptionId id = SubscriptionId.builder()
                .blog(blogRepository.findByAlias(alias).orElse(null))
                .user(user)
                .build();
        Subscription subscription = Subscription.builder()
                .level(1)
                .subId(id)
                .build();
        blog.getSubs().add(subscription);
        subscriptionRepository.save(subscription);
    }
}
