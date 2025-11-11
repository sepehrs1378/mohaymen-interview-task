package com.mohaymen.messaging.controller;

import com.mohaymen.messaging.common.TokenHandler;
import com.mohaymen.messaging.dto.LoginRequest;
import com.mohaymen.messaging.dto.RegisterRequest;
import com.mohaymen.messaging.model.User;
import com.mohaymen.messaging.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final TokenHandler tokenHandler = TokenHandler.getInstance();
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        try {
            User u = userService.register(req.getUid(), req.getPassword());
            return ResponseEntity.ok(u);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        var user = userService.findByUidAndPassword(req.getUid(), req.getPassword());
        if (user.isPresent()) {
            String token = tokenHandler.getToken(req.getUid());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(404).body("user not found");
        }
    }
}