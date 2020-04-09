package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>, JpaRepository<Post, Long> {

    List<Post> findByBlog_Id(Long blog_id);

}
