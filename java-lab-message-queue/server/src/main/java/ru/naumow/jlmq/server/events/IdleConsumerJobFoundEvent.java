package ru.naumow.jlmq.server.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.naumow.jlmq.server.dto.ConsumerDto;

public class IdleConsumerJobFoundEvent extends ApplicationEvent {

    @Getter
    private final ConsumerDto changedConsumerDto;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public IdleConsumerJobFoundEvent(Object source, ConsumerDto consumerDto) {
        super(source);
        this.changedConsumerDto = consumerDto;
    }
}

