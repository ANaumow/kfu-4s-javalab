package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumow.model.User;
import ru.naumow.model.UserStatus;
import ru.naumow.repositories.UserRepository;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void confirm(String confirmId) {
        User user = userRepository.findByConfirmId(confirmId)
                .orElseThrow(() -> new IllegalArgumentException("no user with such uuid"));
        user.setStatus(UserStatus.CONFIRMED);
        userRepository.update(user);
    }
}
