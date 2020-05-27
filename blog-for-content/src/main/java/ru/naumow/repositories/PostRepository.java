package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>, JpaRepository<Post, Long> {

    List<Post> findByBlogId(Long blog_id);

    long countByBlogId(Long blogId);

    List<Post> findByBlogIdAndLevelIsLessThanEqual(Long blogId, int level);

    void deleteByBlogIdAndType(Long blogId, String type);

    List<Post> findByBlog(Blog blog);

    List<Post> findAllByLikesContains(User user);

}
