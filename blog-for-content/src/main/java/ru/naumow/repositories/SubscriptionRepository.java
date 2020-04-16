package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.SubscriptionId;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {
}
