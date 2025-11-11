package com.mohaymen.messaging.repository;

import com.mohaymen.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipientUidOrderByCreatedAtAsc(String recipientUid);
    List<Message> findByRecipientUidAndSenderUidOrderByCreatedAtAsc(String recipientUid, String senderUid);
}