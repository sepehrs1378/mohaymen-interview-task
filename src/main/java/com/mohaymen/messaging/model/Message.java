package com.mohaymen.messaging.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User sender;

    @ManyToOne(optional = false)
    private User recipient;

    @Column(columnDefinition = "TEXT")
    private String content;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.createdAt = OffsetDateTime.now();
    }
}