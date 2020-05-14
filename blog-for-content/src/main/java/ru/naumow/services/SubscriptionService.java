package ru.naumow.services;

import ru.naumow.dto.UserDto;

import java.util.List;

public interface SubscriptionService {

    int subLevelFor(Long blogId, Long userId);

    List<UserDto> subsFor(Long blogId);

}
