package com.example.sportsbuddy.playground;

import com.example.sportsbuddy.turf.TurfDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaygroundDTO {
	
	String id;
	String playgroundType;
	Integer maxBookingSlot;
	private TurfDTO turf;
	
}
