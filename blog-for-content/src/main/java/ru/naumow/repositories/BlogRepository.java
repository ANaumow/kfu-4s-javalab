package ru.naumow.repositories;

import ru.naumow.dto.BlogDto;
import ru.naumow.entity.Blog;

import java.util.Optional;


public interface BlogRepository/* extends CrudRepository<Blog, Long>, JpaRepository<Blog, Long> */{

    Optional<Blog> findByAlias(String alias);

    Optional<Blog> findByOwnerId(Long userId);

    void save(Blog blog);


}
