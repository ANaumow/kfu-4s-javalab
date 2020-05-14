package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.BlogDto;
import ru.naumow.dto.BlogForm;
import ru.naumow.dto.PostDto;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.User;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.PostRepository;
import ru.naumow.repositories.SubscriptionRepository;
import ru.naumow.repositories.UsersRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired public BlogRepository         blogRepository;
    @Autowired public UsersRepository        usersRepository;
    @Autowired public PostRepository         postRepository;
    @Autowired public SubscriptionRepository subscriptionRepository;

    @Autowired private PostService postService;
    @Autowired private SubscriptionService subscriptionService;
    @Autowired private LocalDateTimeResolver timeResolver;

    @Override
    public UserDto getAuthor(BlogDto blogDto) {
        /*return UserDto.from(usersRepository.getUserByBlogAlias(blogDto.getAlias())
                .orElseThrow(IllegalStateException::new));*/
        return null;
    }

    @Override
    public BlogDto getBlogByAlias(String alias) {
        return null;
        /*Blog blog = blogRepository.findByAlias(alias).orElseThrow(IllegalStateException::new);
        List<Post> posts = postRepository.findByBlogId(blog.getId());
        List<Subscription> subs = subscriptionRepository.findBySubIdBlogId(blog.getId());

        return BlogDto.from(
                blog,
                posts,
                subs
        )

        BlogDto blogDto = BlogDto.from();
        blogDto.setPostCount(postRepository.countByBlogId(blogDto.getId()));
        blogDto.setSubCount(subscriptionRepository.countBySubIdBlogId(blogDto.getId()));
        return blogDto;*/
    }

    @Override
    public BlogDto blogDtoForUser(String alias, User user) {
        Blog blog = blogRepository.findByAlias(alias).orElseThrow(IllegalStateException::new);
        int subLevel = isOwner(alias, user) ? 100 : subscriptionService.subLevelFor(blog.getId(), user.getId());
        List<PostDto> postDtoList = postService.postsOfLevel(blog.getId(), subLevel);
        postDtoList.sort((o1, o2) -> o2.getCratedAt().compareTo(o1.getCratedAt()));
        return BlogDto.from(blog, postDtoList);
    }

    @Override
    public String blogAliasOf(User user) {
        return blogRepository.findByOwnerId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("not found"))
                .getAlias();
    }

    @Override
    public boolean ifUserHaveBlog(User user) {
        return blogRepository.findByOwnerId(user.getId()).isPresent();
    }

    @Override
    public Blog createBlog(BlogForm blogForm, User user) {
        Blog blog = Blog.builder()
                .alias(blogForm.getAlias())
                .avatarUrl(blogForm.getAvatarUrl())
                .backgroundUrl(blogForm.getBackgroundUrl())
                .cratedAt(timeResolver.now())
                .owner(user)
                .subTitle(blogForm.getSubTitle())
                .title(blogForm.getTitle())
                .build();

        if (ifUserHaveBlog(user)){
            Blog found = blogRepository.findByOwnerId(user.getId()).orElseThrow(() -> new IllegalArgumentException("not found"));
            blog.setId(found.getId());
        }

        blogRepository.save(blog);
        return blog;
    }

    @Override
    public boolean isOwner(String alias, User user) {
        Blog blog = blogRepository.findByAlias(alias).orElseThrow(IllegalStateException::new);
        return blog.getOwner().equals(user);
    }

    @Override
    public List<PostDto> getRecommendsFor(User user) {
        List<PostDto> postDtoList = PostDto.from(postRepository.findAll());
        Collections.shuffle(postDtoList);
        return postDtoList;
    }


}
