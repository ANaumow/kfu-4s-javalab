package ru.naumow.jlmq.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.jlmq.server.entity.Message;
import ru.naumow.jlmq.server.entity.MessageState;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>, JpaRepository<Message, Long> {

    Optional<Message> getByMessageId(String messageId);

    Optional<Message> findByMessageId(String messageId);

    List<Message> findByState(MessageState state);

    Optional<Message> findFirstByStateOrderByCreatedAtAsc(MessageState state);

}
