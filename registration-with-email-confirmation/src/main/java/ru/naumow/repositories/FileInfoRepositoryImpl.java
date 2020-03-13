package ru.naumow.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.naumow.model.FileInfo;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class FileInfoRepositoryImpl implements FileInfoRepository {

    //language=PostgreSQL
    public static final String SQL_SELECT_BY_ID = "select * from file_info where id = ?";

    //language=PostgreSQL
    public static final String SQL_SELECT_BY_STORAGE_NAME = "select * from file_info where storage_file_name = ?";

    //language=PostgreSQL
    private static final String SQL_UPDATE = "update file_info set storage_file_name = ?, original_filename = ?, size = ?, type = ?, url = ?  where id = ?";

    //language=PostgreSQL
    public static final String SQL_SELECT_ALL = "SELECT * FROM file_info";

    //language=PostgreSQL
    private static final String SQL_DELETE_BY_ID = "delete from file_info where id = ?";

    //language=PostgreSQL
    private static final String SQL_INSERT = "insert into file_info(storage_file_name, original_filename, size, type, url) values (?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .storageFileName(row.getString("storage_file_name"))
                    .originalFileName(row.getString("original_filename"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .path(row.getString("url"))
                    .build();

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, fileInfoRowMapper);
    }

    @Override
    public Optional<FileInfo> findByStorageFileName(String storageFileName) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_STORAGE_NAME, fileInfoRowMapper, storageFileName);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileInfo> find(Long id) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileInfoRowMapper, id);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(FileInfo entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getStorageFileName(), entity.getOriginalFileName(), entity.getSize().toString(), entity.getType(), entity.getType(), entity.getId());
    }

    @Override
    public void save(FileInfo entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getStorageFileName());
            statement.setString(2, entity.getOriginalFileName());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getType());
            statement.setString(5, entity.getPath());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());}

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
