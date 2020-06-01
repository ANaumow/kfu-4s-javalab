package ru.naumow.service;

import ru.naumow.dto.StatisticDto;
import ru.naumow.dto.UserDto;

public interface StorageService {

    void createContent(String type, String body, Long userId);

    UserDto createUser();

    UserDto showUser(Long userId);

    StatisticDto statsForUser(Long userId);

}
