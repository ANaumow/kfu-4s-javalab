package ru.naumow.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.naumow.model.User;
import ru.naumow.model.UserStatus;
import ru.naumow.repositories.UserRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    //language=PostgreSQL
    public static final String SQL_SELECT_BY_ID = "select * from db_user where id = ?";

    //language=PostgreSQL
    public static final String SQL_SELECT_BY_CONFIRM_ID = "select * from db_user where confirm_id = ?";

    //language=PostgreSQL
    public static final String SQL_SELECT_BY_EMAIL = "select * from db_user where email = ?";

    //language=PostgreSQL
    private static final String SQL_UPDATE = "update db_user set email = ?, password = ?, status = ?, confirm_id = ?  where id = ?";

    //language=PostgreSQL
    public static final String SQL_SELECT_ALL = "SELECT * FROM db_user";

    //language=PostgreSQL
    private static final String SQL_DELETE_BY_ID = "delete from db_user where id = ?";

    //language=PostgreSQL
    private static final String SQL_INSERT = "insert into db_user(email, password, status, confirm_id) values (?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .email(row.getString("email"))
                    .password(row.getString("password"))
                    .status(UserStatus.valueOf(row.getString("status")))
                    .confirmId(row.getString("confirm_id"))
                    .build();


    @Override
    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getEmail(), entity.getPassword(), entity.getStatus().toString(), entity.getConfirmId(), entity.getId());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getStatus().toString());
            statement.setString(4, entity.getConfirmId());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByConfirmId(String confirmedId) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CONFIRM_ID, userRowMapper, confirmedId);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAllConfirmed() {
        throw new IllegalStateException("method is not implemented yet");
    }

    @Override
    public List<User> findAllUnconfirmed() {
        throw new IllegalStateException("method is not implemented yet");
    }

}
