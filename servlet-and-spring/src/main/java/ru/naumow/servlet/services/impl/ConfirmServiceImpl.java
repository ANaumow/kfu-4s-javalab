package ru.naumow.servlet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumow.servlet.models.User;
import ru.naumow.servlet.repositories.UserRepository;
import ru.naumow.servlet.services.ConfirmService;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean confirm(String link) {
        Optional<User> userOptional = userRepository.findByLink(link);
        if (userOptional.isPresent()) {
            userRepository.updateState("CONFIRMED", link);
            return true;
        }
        return false;
    }
}
