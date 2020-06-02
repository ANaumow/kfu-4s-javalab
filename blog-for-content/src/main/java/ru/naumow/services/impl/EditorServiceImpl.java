package ru.naumow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.PostDto;
import ru.naumow.events.PostCreatedEvent;
import ru.naumow.exceptions.BlogNotFoundException;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.FileDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.Content;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.ContentRepository;
import ru.naumow.repositories.PostRepository;
import ru.naumow.services.BlogService;
import ru.naumow.services.EditorService;
import ru.naumow.services.FileService;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LocalDateTimeResolver timeResolver;

    @Autowired
    private FileService fileService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public PostDto submitPost(User user, String mdText, Long postId, String type) {
        FileDto fileDto = processMdTextSaving(mdText);

        Content content = Content.builder()
                .url(fileDto.getPublicUrl())
                .build();
        contentRepository.save(content);

        Blog blog = blogRepository.findByOwnerId(user.getId()).orElseThrow(BlogNotFoundException::new);

        Post post = Post.builder()
                .active(true)
                .blog(blog)
                .content(content)
                .cratedAt(timeResolver.now())
                .level(1)
                .type(type)
                .build();

        postRepository.save(post);
        PostDto dto = PostDto.from(post);
        eventPublisher.publishEvent(new PostCreatedEvent(this, dto, blog.getAlias()));
        return dto;
    }

    @Override
    public FileDto processImageSaving(MultipartFile multipartFile) {
        return fileService.saveMultipart(multipartFile);
    }

    @Override
    public FileDto processMdTextSaving(String text) {
        return fileService.saveRawText(text);
    }

    @Override
    public String makeSuccessfulUrl(User user) {
        return blogService.blogInfoByOwner(user).getAlias();
    }

}
