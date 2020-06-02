package ru.naumow.services;

import org.junit.jupiter.api.Test;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.PostRepository;
import ru.naumow.services.impl.RecommendationServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecommendationServiceImplTest {

    @Test
    void recommendationsWithTwoUsers() {
        PostRepository postRepository = mock(PostRepository.class);
        RecommendationServiceImpl service = new RecommendationServiceImpl(postRepository);

        User[] testUsers = createUsers(2);
        Post[] testPosts = createPosts(2);

        like(testPosts[0], testUsers[0]);
        like(testPosts[0], testUsers[1]);

        like(testPosts[1], testUsers[0]);

        when(postRepository.findAllByLikesContains(testUsers[0]))
                .thenReturn(arrayList(testPosts[0], testPosts[1]));

        when(postRepository.findAllByLikesContains(testUsers[1]))
                .thenReturn(arrayList(testPosts[0]));


        List<PostDto> expected = arrayList(PostDto.from(testPosts[1]));
        List<PostDto> actual = service.recommendationsFor(testUsers[1]);

        assertEquals(expected, actual);
    }

    @Test
    void recommendationsWithThreeUsersAndFourPosts() {
        PostRepository postRepository = mock(PostRepository.class);
        RecommendationServiceImpl service = new RecommendationServiceImpl(postRepository);

        User[] testUsers = createUsers(3);
        Post[] testPosts = createPosts(4);

        like(testPosts[0], testUsers[0]);
        like(testPosts[0], testUsers[1]);
        like(testPosts[0], testUsers[2]);

        like(testPosts[1], testUsers[0]);
        like(testPosts[1], testUsers[2]);

        like(testPosts[2], testUsers[1]);

        like(testPosts[3], testUsers[2]);

        when(postRepository.findAllByLikesContains(testUsers[0]))
                .thenReturn(arrayList(testPosts[0], testPosts[1]));

        when(postRepository.findAllByLikesContains(testUsers[1]))
                .thenReturn(arrayList(testPosts[0], testPosts[2]));

        when(postRepository.findAllByLikesContains(testUsers[2]))
                .thenReturn(arrayList(testPosts[0], testPosts[1], testPosts[3]));

        List<PostDto> expected;
        List<PostDto> actual;

        expected = PostDto.from(arrayList(testPosts[3], testPosts[2]));
        actual = service.recommendationsFor(testUsers[0]);
        assertEquals(expected, actual);

        expected = PostDto.from(arrayList(testPosts[1], testPosts[3]));
        actual = service.recommendationsFor(testUsers[1]);
        assertTrue(expected.size() == actual.size() &&
                expected.containsAll(actual) &&
                actual.containsAll(expected));
    }

    @SafeVarargs
    private final <T> List<T> arrayList(T... t) {
        return new ArrayList<>(Arrays.asList(t));
    }

    private void like(Post post, User user) {
        post.getLikes().add(user);
    }

    private User[] createUsers(int n) {
        User[] users = new User[n];
        for (int i = 0; i < n; i++) {
            users[i] = User.builder()
                    .id(i + 1L)
                    .build();
        }
        return users;
    }

    private Post[] createPosts(int n) {
        Post[] posts = new Post[n];
        for (int i = 0; i < n; i++) {
            posts[i] = Post.builder()
                    .id(i + 1L)
                    .likes(new ArrayList<>())
                    .build();
        }
        return posts;
    }

}