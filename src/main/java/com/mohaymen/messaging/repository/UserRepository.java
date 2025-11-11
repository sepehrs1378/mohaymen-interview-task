package com.mohaymen.messaging.repository;

import com.mohaymen.messaging.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUidAndPassword(String uid, String password);
}