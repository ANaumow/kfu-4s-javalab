package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.SubscriptionId;

import java.util.List;
import java.util.Optional;

//@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    long countBySubIdBlogId(Long blogId);

    Optional<Subscription> findBySubIdBlogIdAndSubIdUserId(Long blogId, Long userId);

    List<Subscription> findSubscriptionBySubIdBlogId(Long blogId);

}
