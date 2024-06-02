package com.example.sportsbuddy.playground;

import com.example.sportsbuddy.auth.config.appConstants;
import com.example.sportsbuddy.response.PlaygroundResponse;
import com.example.sportsbuddy.turf.TurfDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/playground")
public class PlaygroundController {
	
	private final PlaygroundService ps;
	
	public PlaygroundController(PlaygroundService pService){
		this.ps = pService;
	}
	
	@PostMapping("/create/turf/{tid}")
	@ResponseStatus(HttpStatus.CREATED)
	public PlaygroundDTO createPlaygrounds(@RequestBody PlaygroundDTO dto, @PathVariable String tid){
		return this.ps.createPlayground(dto,tid);
	}
	
	@GetMapping("/all")
	public ResponseEntity<PlaygroundResponse> showAllPlayground(
			@RequestParam(value="pageNumber",defaultValue = appConstants.PAGE_NUMBER, required = false) Integer pagenum,
			@RequestParam(value="pageSize",defaultValue = appConstants.PAGE_SIZE, required = false) Integer pagesize,
			@RequestParam(value="sortBy",defaultValue = appConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value="sortOrder",defaultValue = appConstants.SORT_ORDER,required = false)String sortOrder
	){
		PlaygroundResponse playgroundResponse = this.ps.findAllPlayground(pagenum,pagesize,sortBy,sortOrder);
		return new ResponseEntity<>(playgroundResponse,HttpStatus.OK);
	}
	
	@GetMapping("/turf/{turfname}/{turfid}")
	@ResponseStatus(HttpStatus.OK)
	public List<PlaygroundDTO> getAllPlaygroundsByTurfName(@PathVariable String turfname, @PathVariable String turfid){
		return this.ps.findPlaygroundByTurf(turfname, turfid);
	}
	
}
