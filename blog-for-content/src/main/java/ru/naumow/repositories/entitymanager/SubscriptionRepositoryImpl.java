package ru.naumow.repositories.entitymanager;

import org.springframework.stereotype.Repository;
import ru.naumow.entity.Subscription;
import ru.naumow.repositories.SubscriptionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Subscription entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Subscription entity) {
        entityManager.remove(entity);
    }
}
