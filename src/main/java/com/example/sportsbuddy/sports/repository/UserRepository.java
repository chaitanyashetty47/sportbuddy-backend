package com.example.sportsbuddy.sports.repository;

import com.example.sportsbuddy.user.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;




public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}
