package com.mohaymen.messaging.controller;

import com.mohaymen.messaging.common.TokenHandler;
import com.mohaymen.messaging.dto.SendMessageRequest;
import com.mohaymen.messaging.model.Message;
import com.mohaymen.messaging.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final TokenHandler tokenHandler = TokenHandler.getInstance();
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody SendMessageRequest req) {
        var uid = tokenHandler.extractUid(authHeader);
        try {
            Message m = messageService.sendMessage(uid, req.getToUid(), req.getContent());
            return ResponseEntity.ok(m);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/inbox")
    public ResponseEntity<Map<String, List<Message>>> inboxGrouped(@RequestHeader("Authorization") String authHeader) {
        var uid = tokenHandler.extractUid(authHeader);
        return ResponseEntity.ok(messageService.getInboxGroupedBySender(uid));
    }

    @GetMapping("/from/{senderUid}")
    public ResponseEntity<?> messagesFrom(@RequestHeader("Authorization") String authHeader,
            @PathVariable String senderUid) {
        var uid = tokenHandler.extractUid(authHeader);
        return ResponseEntity.ok(messageService.getMessagesFromSender(uid, senderUid));
    }
}