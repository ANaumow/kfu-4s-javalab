package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
