package ru.naumow.jlmq.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "m_id")
    private String messageId;

    @Enumerated(EnumType.STRING)
    private MessageState state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    private Queue queue;

    private String body;

}
