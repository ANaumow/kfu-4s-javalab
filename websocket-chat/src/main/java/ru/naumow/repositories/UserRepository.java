package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumow.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
