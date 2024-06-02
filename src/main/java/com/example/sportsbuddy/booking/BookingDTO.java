package com.example.sportsbuddy.booking;

import com.example.sportsbuddy.playground.Playground;
import com.example.sportsbuddy.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
	private String id;

	private User user;
	
	private Playground playground;
	
	private LocalDate date;
	
	private LocalTime StartTime;
	
	private LocalTime EndTime;
	
}
