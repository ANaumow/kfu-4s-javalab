package ru.naumow.repositories.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.naumow.models.Course;
import ru.naumow.repositories.CoursesRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component()
public class CoursesRepositoryJdbcTemplateImpl implements CoursesRepository {

    //language=PostgreSQL
    public static final String SQL_SELECT_BY_ID = "select * from course where id = ?";

    //language=PostgreSQL
    private static final String SQL_UPDATE = "update course set title = ? where id = ?";

    //language=PostgreSQL
    public static final String SQL_SELECT_ALL = "SELECT * FROM course";

    //language=PostgreSQL
    private static final String SQL_DELETE_BY_ID = "delete from course where id = ?";

    //language=PostgreSQL
    private static final String SQL_INSERT = "insert into course(title) values (?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Course> courseRowMapper = (row, rowNumber) ->
            Course.builder()
                    .id(row.getLong("id"))
                    .title(row.getString("title"))
                    .build();


    @Override
    public Optional<Course> find(Long id) {
        try {
            Course course = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, courseRowMapper);
            return Optional.ofNullable(course);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Course entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getTitle(), entity.getId());
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, courseRowMapper);
    }

    @Override
    public void save(Course entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getTitle());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
