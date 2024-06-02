package com.example.sportsbuddy.playground;

import com.example.sportsbuddy.turf.Turf;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playground {
	@Id
	@GeneratedValue
	String id;
	
	String playgroundType;
	
	Integer maxBookingSlot;
	
	@ManyToOne
	Turf turf;

}
