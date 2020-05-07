package ru.naumow.jlmq.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.naumow.jlmq.server.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JlmqMessageDto {

    public enum JlmqCommand {
        @JsonProperty("send") SEND,
        @JsonProperty("subscribe") SUBSCRIBE,
        @JsonProperty("receive") RECEIVE,
        @JsonProperty("accepted") ACCEPTED,
        @JsonProperty("completed") COMPLETED
    }

    private JlmqCommand command;

    private String queueName;

    @Nullable
    private String messageId;

    @Nullable
    private Object body;

    public static JlmqMessageDto from(Message message) {
        return JlmqMessageDto.builder()
                .messageId(message.getMessageId())
                .body(message.getBody())
                .queueName(message.getQueue().getName())
                .build();
    }

    public static List<JlmqMessageDto> from(List<Message> consumers) {
        return consumers.stream()
                .map(JlmqMessageDto::from)
                .collect(Collectors.toList());
    }

}
