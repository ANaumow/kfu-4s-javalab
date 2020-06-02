package ru.naumow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.exceptions.BlogNotFoundException;
import ru.naumow.exceptions.PostNotFoundException;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.*;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Post;
import ru.naumow.entity.Subscription;
import ru.naumow.entity.User;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.PostRepository;
import ru.naumow.services.*;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    public BlogRepository blogRepository;

    @Autowired
    public PostRepository postRepository;

    @Autowired
    private LocalDateTimeResolver timeResolver;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FileService fileService;

    @Override
    @Transactional
    public BlogDto blogForUserView(String alias, User user) {
        Blog blog = findBlogByAlias(alias);
        return blogForUserView(blog, user);
    }

    private BlogDto blogForUserView(Blog blog, User user) {
        int subLevel;
        if (isOwner(blog, user)) {
            subLevel = 100;
        } else {
            subLevel = blog.getSubs().stream()
                    .filter(s -> s.getUser().getId().equals(user.getId()))
                    .map(Subscription::getLevel)
                    .findFirst()
                    .orElse(0);
        }

        List<PostDto> postDtoList = postService.filteredPostsOfBlog(blog.getId(), subLevel);
        postDtoList.sort((o1, o2) -> o2.getCratedAt().compareTo(o1.getCratedAt()));
        return BlogDto.from(blog, postDtoList);
    }

    @Override
    @Transactional
    public BlogDto createBlog(BlogForm blogForm, User user) {
        FileDto avatar = fileService.saveMultipart(blogForm.getAvatar());
        FileDto background = fileService.saveMultipart(blogForm.getBackground());
        Blog blog = Blog.builder()
                .alias(blogForm.getAlias())
                .avatarUrl(avatar.getPublicUrl())
                .backgroundUrl(background.getPublicUrl())
                .cratedAt(timeResolver.now())
                .owner(user)
                .subTitle(blogForm.getSubTitle())
                .title(blogForm.getTitle())
                .build();
        blogRepository.findByOwnerId(user.getId())
                .ifPresent(found -> blog.setId(found.getId()));
        blogRepository.save(blog);

        return blogForUserView(blog, user);
    }

    private boolean isOwner(Blog blog, User user) {
        return blog.getOwner().getId().equals(user.getId());
    }

    @Override
    public BlogInfo blogInfoByOwner(User user) {
        Blog blog = findBlogByOwner(user);
        return BlogInfo.from(blog);
    }

    @Override
    @Transactional
    public LikeResponse like(User user, Long postId) {
        Post post = findPostById(postId);
        return likeService.toggleLike(user, post);
    }

    @Override
    @Transactional
    public SubscriptionResponse subscribe(User user, String alias) {
        Blog blog = findBlogByAlias(alias);
        return subscriptionService.toggleSubscription(user, blog);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    private Blog findBlogByOwner(User owner) {
        return blogRepository.findByOwnerId(owner.getId()).orElseThrow(BlogNotFoundException::new);
    }

    private Blog findBlogByAlias(String alias) {
        return blogRepository.findByAlias(alias).orElseThrow(BlogNotFoundException::new);
    }

}
