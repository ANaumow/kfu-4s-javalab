package ru.naumow.services;

import ru.naumow.dto.UserDto;

public interface SecurityService {

    String createSecurityToken(String login);

    UserDto authorizeByToken(String token);

}
