package com.example.sportsbuddy.sports;

import com.example.sportsbuddy.user.User;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface SportsRepository extends MongoRepository<Sports, String> {
	
	List<Sports> findByUser(Optional<User> user);

}
