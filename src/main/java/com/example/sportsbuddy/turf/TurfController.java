package com.example.sportsbuddy.turf;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/turf")
public class TurfController {

	public final TurfService turfS ;
	
	public TurfController(TurfService turfS) {
		this.turfS = turfS;
	}
	
	@GetMapping("/all")
	public List<TurfDTO> showAllTurfs(){
		return this.turfS.findAllTurf();
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public TurfDTO createTurf(@RequestBody TurfDTO dto){
		return this.turfS.createTurf(dto);
	}
	

}
