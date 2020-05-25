package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Subscription;/*
import ru.naumow.entity.SubscriptionId;*/

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    void save(Subscription entity);

    void delete(Subscription entity);

}
