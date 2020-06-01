package ru.naumow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.dto.StatisticDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entities.Content;
import ru.naumow.entities.User;
import ru.naumow.repositories.ContentRepository;
import ru.naumow.repositories.UserRepository;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void createContent(String type, String body, Long userId) {
        User owner = userRepository.getOne(userId);
        Content content = Content.builder()
                .body(body)
                .owner(owner)
                .type(type)
                .build();
        contentRepository.save(content);
    }

    @Transactional
    @Override
    public UserDto showUser(Long userId) {
        User target = userRepository.getOne(userId);
        return UserDto.from(target);
    }

    @Transactional
    @Override
    public UserDto createUser() {
        User user = new User();
        userRepository.save(user);
        return UserDto.from(user);
    }

    @Override
    @Transactional
    public StatisticDto statsForUser(Long userId) {
        User user = userRepository.getOne(userId);
        StatisticDto statsByUser = contentRepository.getStatsByUser(user);
        return statsByUser;
    }

}
