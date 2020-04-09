package ru.naumow.repositories;

import ru.naumow.entity.User;

import java.util.Optional;

public interface UsersRepositoryCustom {

    Optional<User> getUserByBlogAlias(String alias);

}
