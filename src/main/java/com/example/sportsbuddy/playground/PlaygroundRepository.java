package com.example.sportsbuddy.playground;

import com.example.sportsbuddy.turf.Turf;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaygroundRepository extends MongoRepository<Playground,String> {
	 List<Playground> findByTurf(Turf turf);
}
