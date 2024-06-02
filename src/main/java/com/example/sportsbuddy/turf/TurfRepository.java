package com.example.sportsbuddy.turf;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TurfRepository extends MongoRepository<Turf,String> {
	@Query("{name: ?0, id: ?1}")
	Turf findByNameandId(String name, String ID);
}
