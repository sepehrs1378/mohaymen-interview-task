package com.mohaymen.messaging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String uid;

    @NotBlank
    private String password;
}