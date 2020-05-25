package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.User;

import java.util.Collections;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private PostService postService;

    @Override
    public List<PostDto> recommendationsFor(User user) {
        List<PostDto> postDtoList = postService.findAll();
        Collections.shuffle(postDtoList);
        return postDtoList;
    }

}