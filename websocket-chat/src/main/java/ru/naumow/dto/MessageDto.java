package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long   id;
    private String text;
    private String fromUsername;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .fromUsername(message.getUser().getLogin())
                .text(message.getText())
                .build();
    }

    public static List<MessageDto> from(List<Message> messages) {
        return messages.stream().map(MessageDto::from).collect(Collectors.toList());
    }
}
