package com.example.sportsbuddy.sports;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportsController {
	
	private final SportsService sportsS;
	
	private final ModelMapper mMap;
	
	public SportsController(SportsService sportsS, ModelMapper map ){
		this.sportsS = sportsS;
		this.mMap = map;
	}
	
	@PostMapping("/createSports")
	@ResponseStatus(HttpStatus.CREATED)
	public SportsDTO createSport(@RequestBody SportsDTO dto){
		return this.sportsS.createSports(dto);
	
	}
	
	@GetMapping("/getAll")
	@ResponseStatus(HttpStatus.OK)
	public List<SportsDTO> getAllSports(){
		return this.sportsS.getAllSports();
	}
}
