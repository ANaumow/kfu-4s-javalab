package ru.naumow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long> {
}
