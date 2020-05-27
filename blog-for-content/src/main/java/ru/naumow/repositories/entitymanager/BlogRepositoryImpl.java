//package ru.naumow.repositories.entitymanager;
//
//import org.springframework.stereotype.Repository;
//import ru.naumow.entity.Blog;
//import ru.naumow.repositories.BlogRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.Optional;
//
//@Repository
//public class BlogRepositoryImpl implements BlogRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Optional<Blog> findByAlias(String alias) {
//        Query query = entityManager.createQuery("select b from Blog b where alias = :alias");
//        query.setParameter("alias", alias);
//        try {
//            return Optional.of((Blog) query.getSingleResult());
//        } catch (NoResultException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public Optional<Blog> findByOwnerId(Long userId) {
//        Query query = entityManager.createQuery("select b from Blog b where owner.id = :userId");
//        query.setParameter("userId", userId);
//        try {
//            return Optional.of((Blog) query.getSingleResult());
//        } catch (NoResultException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public void save(Blog blog) {
//        entityManager.persist(blog);
//    }
//
//}
