package ru.naumow.events;

import org.springframework.context.ApplicationEvent;
import ru.naumow.dto.PostDto;

public class PostCreatedEvent extends ApplicationEvent {

    private final PostDto postDto;
    private final String  blogAlias;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public PostCreatedEvent(Object source, PostDto postDto, String blogAlias) {
        super(source);
        this.postDto = postDto;
        this.blogAlias = blogAlias;
    }

    public PostDto getPostDto() {
        return postDto;
    }

    public String getBlogAlias() {
        return blogAlias;
    }
}
