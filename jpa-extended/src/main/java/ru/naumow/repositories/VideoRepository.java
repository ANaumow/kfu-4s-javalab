package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entities.VideoChannel;

@Repository
public interface VideoRepository extends JpaRepository<VideoChannel, Long> {

}
