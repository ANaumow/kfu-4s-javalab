package ru.naumow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.ImageContent;

@Repository
public interface ContentRepository extends JpaRepository<ImageContent, Long> {
}
