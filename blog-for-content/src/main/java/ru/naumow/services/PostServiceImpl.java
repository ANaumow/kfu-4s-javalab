package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.exceptions.PostNotFoundException;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.CommentDto;
import ru.naumow.dto.LikeResponse;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.PostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private LocalDateTimeResolver timeResolver;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeService likeService;

    @Autowired
    private CommentService commentService;

    @Override
    public List<PostDto> filteredPostsOfBlog(Long blogId, int level) {
        List<Post> posts = postRepository.findByBlogIdAndLevelIsLessThanEqual(blogId, level);
        return PostDto.from(posts);
    }

    @Override
    @Transactional
    public LikeResponse toggleLike(User user, Long postId) {
        return likeService.toggleLike(user, findPostById(postId));
    }

    @Override
    public List<CommentDto> commentsByPost(Long postId, boolean doWait) {
        return commentService.commentsByPost(findPostById(postId), doWait);
    }

    @Override
    public CommentDto submitComment(User user, Long postId, String text) {
        return commentService.submitComment(user, findPostById(postId), text);
    }

    @Override
    public List<PostDto> findAll() {
        return PostDto.from(postRepository.findAll());
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

}
