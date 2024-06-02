package com.example.sportsbuddy.booking;

import com.example.sportsbuddy.playground.Playground;
import com.example.sportsbuddy.turf.Turf;
import com.example.sportsbuddy.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Document
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue
	private String id;
	
	@OneToOne
	private User user;
	
	@ManyToOne
	private Turf turf;
	
	@ManyToOne
	private Playground playground;
	
	private LocalDate date;
	
	private LocalTime StartTime;
	
	private LocalTime EndTime;
}
