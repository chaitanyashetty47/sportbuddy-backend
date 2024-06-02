package com.example.sportsbuddy.booking;

import com.example.sportsbuddy.auth.config.JwtService;
import com.example.sportsbuddy.exceptions.TimeAlreadyBookedException;
import com.example.sportsbuddy.playground.Playground;
import com.example.sportsbuddy.playground.PlaygroundDTO;
import com.example.sportsbuddy.playground.PlaygroundRepository;
import com.example.sportsbuddy.sports.repository.UserRepository;
import com.example.sportsbuddy.turf.Turf;
import com.example.sportsbuddy.turf.TurfRepository;
import com.example.sportsbuddy.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class BookingService {
	
	
	private final TurfRepository tRepo;
	
	private final MongoTemplate mongoTemplate;
	private final PlaygroundRepository pRepo;
	private final BookingRepository bRepo;
	
	private final UserRepository uRepo;
	
	private final JwtService jwt;
 	private final ModelMapper mmap;
	
	public BookingService(TurfRepository tRepo, PlaygroundRepository pRepo, BookingRepository bRepo, ModelMapper mmap, UserRepository uRepo, JwtService jwt, MongoTemplate mongoTemp) {
		this.tRepo = tRepo;
		this.pRepo = pRepo;
		this.bRepo = bRepo;
		this.uRepo = uRepo;
		this.mmap = mmap;
		this.jwt = jwt;
		this.mongoTemplate = mongoTemp;
	}

	   BookingDTO createBookings(HttpServletRequest req, BookingDTO dto, String turfName,String turfID, String playgroundId){
		Turf turf = this.tRepo.findByNameandId(turfName,turfID);
		
		Optional<Playground> optionalPlayground = this.pRepo.findById(playgroundId);
		Playground playground = optionalPlayground.orElse(new Playground());
		
		String authorizationHeader = req.getHeader("Authorization");
		   if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			   String token = authorizationHeader.substring(7); // Assuming "Bearer " prefix
			   String email = this.jwt.extractUsername(token);
			   Optional<User> optionalUser = this.uRepo.findByEmail(email);
			   User user = optionalUser.orElse(new User());
			
			   // Check if the time slot is available
			   if (isTimeSlotAvailable(turf, playground, dto.getDate(), dto.getStartTime(), dto.getEndTime())) {
				   Booking booking = this.mmap.map(dto, Booking.class);
				   booking.setTurf(turf);
				   booking.setPlayground(playground);
				   booking.setUser(user);
				   this.bRepo.save(booking);
				   return this.mmap.map(booking, BookingDTO.class);
			   } else {
				   //throw new RuntimeException("The requested time slot is already booked!");
				   throw new TimeAlreadyBookedException("Time slot already booked");
			   }
			   
		   } else {
			   throw new RuntimeException("Token not found!!");
			   
		   }
		   
	}
	
	private boolean isTimeSlotAvailable(Turf turf, Playground playground, LocalDate date, LocalTime startTime, LocalTime endTime) {
		Query query = new Query(Criteria.where("turf").is(turf)
				.and("playground").is(playground)
				.and("date").is(date)
				.andOperator(
						Criteria.where("StartTime").lte(startTime).and("EndTime").gte(endTime),
						Criteria.where("StartTime").lt(endTime).and("EndTime").gt(startTime)
				)
		);
		
		long count = mongoTemplate.count(query, Booking.class);
		return count == 0;
	}
	
	
	
}
