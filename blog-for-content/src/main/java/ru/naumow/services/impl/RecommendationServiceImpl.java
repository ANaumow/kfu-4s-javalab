package ru.naumow.services.impl;

import org.springframework.stereotype.Service;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.PostRepository;
import ru.naumow.services.RecommendationService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private PostRepository postRepository;

    public RecommendationServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Forms recommendations for {@link User}.
     * Recommendations are posts that
     *  - have not been liked by user yet;
     *  - are based on other users preferences;
     *  - sorted by interest (for the first ones is more likely to be liked).
     * Finds users who liked the same posts like the current user,
     * sorts they by matching count and returns that user's favourite posts without duplication
     * @param user user for whom recommendations are formed
     * @return recommendations as list of postDto
     */
    @Override
    public List<PostDto> recommendationsFor(User user) {
        Map<User, Long> similarityMap = new HashMap<>();

        List<Post> targetUserFavouritePosts = postRepository.findAllByLikesContains(user);

        for (Post post : targetUserFavouritePosts) {
            for (User postLikedUser : post.getLikes()) {
                if (postLikedUser.equals(user))
                    continue;
                Long prevValue = similarityMap.getOrDefault(postLikedUser, 0L);
                similarityMap.put(postLikedUser, prevValue + 1);
            }
        }

        List<User> users = similarityMap.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Post> recommendations = new ArrayList<>();

        for (User similarUser : users) {
            List<Post> suppose = postRepository.findAllByLikesContains(similarUser);
            for (Post post : suppose) {
                if (!targetUserFavouritePosts.contains(post) && !recommendations.contains(post))
                    recommendations.add(post);
            }
        }

        return PostDto.from(recommendations);
    }

}