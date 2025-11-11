package com.mohaymen.messaging.service;

import com.mohaymen.messaging.model.Message;
import com.mohaymen.messaging.model.User;
import com.mohaymen.messaging.repository.MessageRepository;
import com.mohaymen.messaging.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message sendMessage(String fromUid, String toUid, String content) {
        User sender = userRepository.findById(fromUid).orElseThrow(() -> new NoSuchElementException("sender not found"));
        User recipient = userRepository.findById(toUid).orElseThrow(() -> new NoSuchElementException("recipient not found"));
        Message m = new Message(sender, recipient, content);
        return messageRepository.save(m);
    }

    public List<Message> getInboxForUser(String userUid) {
        return messageRepository.findByRecipientUidOrderByCreatedAtAsc(userUid);
    }

    public List<Message> getMessagesFromSender(String userUid, String senderUid) {
        return messageRepository.findByRecipientUidAndSenderUidOrderByCreatedAtAsc(userUid, senderUid);
    }

    // convenience: group messages by sender uid
    public Map<String, List<Message>> getInboxGroupedBySender(String userUid) {
        List<Message> all = getInboxForUser(userUid);
        return all.stream().collect(Collectors.groupingBy(m -> m.getSender().getUid(), LinkedHashMap::new, Collectors.toList()));
    }
}