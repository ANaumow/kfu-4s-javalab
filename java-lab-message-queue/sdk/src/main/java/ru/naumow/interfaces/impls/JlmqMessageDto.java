package ru.naumow.interfaces.impls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class is used for serialization and deserialization
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JlmqMessageDto {

    private JlmqCommand command;
    private String      messageId;
    private String      queueName;
    private Object      body;

}
