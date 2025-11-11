package com.mohaymen.messaging.service;

import com.mohaymen.messaging.model.User;
import com.mohaymen.messaging.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String uid, String password) {
        if (userRepository.existsById(uid)) {
            throw new IllegalArgumentException("uid already exists");
        }
        User u = new User(uid, password);
        return userRepository.save(u);
    }

    public Optional<User> findByUid(String uid) {
        return userRepository.findById(uid);
    }

    public Optional<User> findByUidAndPassword(String uid, String password) {
        return userRepository.findByUidAndPassword(uid, password);
    }
}