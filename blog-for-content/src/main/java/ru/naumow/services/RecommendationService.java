package ru.naumow.services;

import ru.naumow.dto.PostDto;
import ru.naumow.entity.User;

import java.util.List;

public interface RecommendationService {

    List<PostDto> recommendationsFor(User user);

}
