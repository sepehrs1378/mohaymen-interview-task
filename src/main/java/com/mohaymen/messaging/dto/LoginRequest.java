package com.mohaymen.messaging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String uid;

    @NotBlank
    private String password;
}