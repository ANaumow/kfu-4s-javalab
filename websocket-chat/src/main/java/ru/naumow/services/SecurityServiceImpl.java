package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.repositories.UserRepository;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired private Environment env;
    @Autowired private UserService userService;

    @Override
    public String createSecurityToken(String login) {
        String secret = env.getProperty("security.token.secret");
        String signature = String.valueOf((login + secret).hashCode());
        return login + "." + signature;
    }

    @Override
    public UserDto authorizeByToken(String token) {
        String[] temp = token.split("\\.");
        if (temp.length != 2) {
            return null;
        }
        String login = temp[0];
        String signature = temp[1];
        String secret = env.getProperty("security.token.secret");
        return String.valueOf((login + secret).hashCode()).equals(signature) ?
                userService.getUserByLogin(login) : null;
    }


}
