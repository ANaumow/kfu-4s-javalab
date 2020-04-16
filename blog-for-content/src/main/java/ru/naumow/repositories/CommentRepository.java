package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumow.entity.Comment;
import ru.naumow.entity.UserPostId;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long id);
}
