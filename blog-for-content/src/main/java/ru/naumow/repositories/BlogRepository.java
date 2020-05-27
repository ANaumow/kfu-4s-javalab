package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.naumow.dto.BlogDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.User;

import java.util.List;
import java.util.Optional;


public interface BlogRepository extends CrudRepository<Blog, Long>, JpaRepository<Blog, Long> {

    Optional<Blog> findByAlias(String alias);

    Optional<Blog> findByOwnerId(Long userId);

    /*void save(Blog blog);*/

    List<Blog> findAllBySubsUser(User user);

}
