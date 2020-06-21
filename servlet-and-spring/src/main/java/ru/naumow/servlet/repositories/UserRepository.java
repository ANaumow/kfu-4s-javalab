package ru.naumow.servlet.repositories;

import ru.naumow.servlet.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Integer, User> {
    Optional<User> findByLink(String link);

    void updateState(String state, String userLink);
}
