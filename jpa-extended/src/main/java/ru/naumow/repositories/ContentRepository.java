package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.naumow.dto.StatisticDto;
import ru.naumow.entities.Content;
import ru.naumow.entities.User;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("select new ru.naumow.dto.StatisticDto(user.id, user.images.size, user.music.size, user.videos.size) from User user where user = :user")
    StatisticDto getStatsByUser(User user);

}
