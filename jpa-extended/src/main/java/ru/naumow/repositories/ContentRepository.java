package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.naumow.entities.Content;
import ru.naumow.entities.Stats;

@Repository
public interface ContentRepository  extends JpaRepository<Content, Long> {

    // JPQL
    @Query("select new ru.naumow.entities.Stats(min(c.id)) from Content c")
    Stats getStats();

}
