package com.example.sportsbuddy.booking;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	
	private final BookingService bs;
	
	public BookingController(BookingService books) {
		this.bs = books;
	}
	
	@PostMapping("/turf/{turfName}/{turfId}/playground/{pgId}/create")
	@ResponseStatus(HttpStatus.CREATED)
	private BookingDTO createBooking(HttpServletRequest req, @RequestBody BookingDTO dto ,@PathVariable String turfName,@PathVariable String turfId,@PathVariable String pgId){
		return this.bs.createBookings(req, dto, turfName, turfId, pgId);
	}
	
	
}
