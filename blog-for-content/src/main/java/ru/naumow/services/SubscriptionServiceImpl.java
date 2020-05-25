package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.dto.SubscriptionResponse;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.User;
import ru.naumow.repositories.SubscriptionRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public SubscriptionResponse toggleSubscription(User user, Blog blog) {
        SubscriptionResponse response = new SubscriptionResponse();
        List<Subscription> blogSubs = blog.getSubs();

        Predicate<Subscription> predicateByUser = subscription ->
                subscription.getUser().getId().equals(user.getId());

        Optional<Subscription> foundSub = blogSubs.stream()
                .filter(predicateByUser)
                .findFirst();

        if (foundSub.isPresent()){
            Subscription sub = foundSub.get();
            blogSubs.remove(sub);
            response.setSubscribed(false);
            subscriptionRepository.delete(sub);
        } else {
            Subscription sub = Subscription.builder()
                    .level(1)
                    .user(user)
                    .build();
            blogSubs.add(sub);
            response.setSubscribed(true);
            subscriptionRepository.save(sub);
        }
        response.setSubscriptionCount(blogSubs.size());
        return response;
    }

}
