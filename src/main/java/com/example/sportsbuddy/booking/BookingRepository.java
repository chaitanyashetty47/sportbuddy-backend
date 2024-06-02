package com.example.sportsbuddy.booking;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking,String> {

}
