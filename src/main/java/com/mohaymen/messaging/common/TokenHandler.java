package com.mohaymen.messaging.common;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// Singleton
public class TokenHandler {
    private static final TokenHandler INSTANCE = new TokenHandler();

    private TokenHandler() {
    }

    public static TokenHandler getInstance() {
        return INSTANCE;
    }

    public String getToken(String uid) {
        return Base64.getEncoder().encodeToString(uid.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUid(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        String base64uid = authHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(base64uid);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
