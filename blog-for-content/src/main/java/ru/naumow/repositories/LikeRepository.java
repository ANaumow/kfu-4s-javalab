package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumow.entity.Like;
import ru.naumow.entity.UserPostId;

public interface LikeRepository extends JpaRepository<Like, UserPostId> {


}
