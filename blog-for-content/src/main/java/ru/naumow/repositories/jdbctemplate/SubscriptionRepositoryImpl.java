package ru.naumow.repositories.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Subscription;
import ru.naumow.repositories.SubscriptionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    /*private final String SQL_INSERT = "INSERT INTO sub(level, user_id) VALUES (?, ?)";

    @SuppressWarnings("FieldCanBeLocal")
    private final String SQL_DELETE = "DELETE FROM sub WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Subscription entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, entity.getLevel());
            statement.setLong(2, entity.getUser().getId());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Subscription entity) {
        jdbcTemplate.update(SQL_DELETE, entity.getId());
    }*/

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
