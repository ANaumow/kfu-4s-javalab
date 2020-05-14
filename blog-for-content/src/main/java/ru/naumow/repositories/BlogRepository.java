package ru.naumow.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Blog;

import java.util.Optional;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long>, JpaRepository<Blog, Long> {

    Optional<Blog> findByAlias(String alias);

    Optional<Blog> findByOwnerId(Long userId);

}
