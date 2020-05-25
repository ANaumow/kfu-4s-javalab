package ru.naumow.repositories.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Content;
import ru.naumow.repositories.ContentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;

@Repository
public class ContentRepositoryImpl implements ContentRepository {

    /*private final String SQL_INSERT = "INSERT INTO content(url) VALUES (?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Content entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getUrl());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Content content) {
        entityManager.persist(content);
    }
}
