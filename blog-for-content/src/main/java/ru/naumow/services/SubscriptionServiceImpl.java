package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.User;
import ru.naumow.repositories.SubscriptionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired private SubscriptionRepository subscriptionRepository;

    @Override
    public int subLevelFor(Long blogId, Long userId) {
        return subscriptionRepository.findBySubIdBlogIdAndSubIdUserId(blogId, userId)
                .map(Subscription::getLevel).orElse(0);
    }

    @Override
    public List<UserDto> subsFor(Long blogId) {
        List<Subscription> subscriptions = subscriptionRepository.findSubscriptionBySubIdBlogId(blogId);
        List<User> users = subscriptions.stream()
                .map(subscription -> subscription.getSubId().getUser())
                .collect(Collectors.toList());
        return UserDto.from(users);
    }
}
