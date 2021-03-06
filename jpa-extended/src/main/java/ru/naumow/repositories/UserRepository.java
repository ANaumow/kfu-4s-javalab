package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
