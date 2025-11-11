package com.mohaymen.messaging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendMessageRequest {
    @NotBlank
    private String toUid;

    @NotBlank
    private String content;
}