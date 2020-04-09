package ru.naumow.repositories;

import org.springframework.stereotype.Repository;
import ru.naumow.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class UsersRepositoryCustomImpl implements UsersRepositoryCustom {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Optional<User> getUserByBlogAlias(String alias) {
        Query query = entityManager.createQuery("from User where blog.alias = :alias", User.class);
        query.setParameter("alias", alias);
        return Optional.ofNullable((User) query.getSingleResult());
    }


}
