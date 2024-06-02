package com.example.sportsbuddy.turf;

import com.example.sportsbuddy.playground.PlaygroundDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class TurfDTO {
	
	@NotEmpty
	String id;
	
	@NotEmpty
	String name;
	@NotEmpty
	@Max(20)
	Integer count;
	@NotEmpty
	String address;
	@NotEmpty
	String city;
	Set<PlaygroundDTO> playgrounds = new HashSet<>();
	
}
