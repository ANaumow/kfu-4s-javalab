package ru.naumow.repositories.entitymanager;

import org.springframework.stereotype.Repository;
import ru.naumow.entity.Content;
import ru.naumow.repositories.ContentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ContentRepositoryImpl implements ContentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Content content) {
        entityManager.persist(content);
    }
}
