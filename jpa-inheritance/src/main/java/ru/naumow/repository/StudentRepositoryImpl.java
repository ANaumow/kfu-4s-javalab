package ru.naumow.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.naumow.model.Student;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final String SQL_CREATE_TABLE = "" +
            "create table if not exists db_user(id serial primary key, name varchar);" +
            "create table if not exists student(mark int) inherits (db_user);";

    private final String SQL_SAVE_STUDENT = "" +
            "insert into student (name, mark) values (?, ?)";

    private final String SQL_UPDATE_STUDENT = "" +
            "update student set name = ?, mark = ? where id = ?";

    private final String SQL_FIND_BY_ID_STUDENT = "" +
            "select * from student where id = ?";

    private final String SQL_DELETE_STUDENT = "" +
            "delete from student where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> rowMapper = (resultSet, i) -> Student.builder()
            .mark(resultSet.getInt("mark"))
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .build();

    @PostConstruct
    public void init() {
        jdbcTemplate.execute(SQL_CREATE_TABLE);
    }

    @Override
    public void save(Student entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_SAVE_STUDENT);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getMark());
            return statement;
        }, keyHolder);
        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public Student findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID_STUDENT, rowMapper, id);
    }

    @Override
    public void update(Student entity) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STUDENT);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getMark());
            statement.setLong(3, entity.getId());
            return statement;
        });
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_STUDENT, id);
    }

}
