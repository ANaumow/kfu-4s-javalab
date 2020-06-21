package ru.naumow.jlmq.server.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.naumow.jlmq.server.entity.Message;

public class AwaitingMessageFoundEvent extends ApplicationEvent {

    @Getter
    private final Message message;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AwaitingMessageFoundEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }

}
