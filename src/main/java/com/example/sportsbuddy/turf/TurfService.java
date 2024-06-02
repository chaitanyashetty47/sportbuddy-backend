package com.example.sportsbuddy.turf;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurfService {
	
	private final ModelMapper modelM;
	
	private final TurfRepository turfR;
	
	public TurfService(ModelMapper modelM, TurfRepository turf) {
		this.modelM = modelM;
		this.turfR = turf;
	}
	
	public TurfDTO createTurf(TurfDTO turfDTO){
		Turf turf = this.modelM.map(turfDTO,Turf.class);
		this.turfR.save(turf);
		return this.modelM.map(turf,TurfDTO.class);
	}
	
	public List<TurfDTO> findAllTurf(){
		List<Turf> allTurfs = this.turfR.findAll();
		List<TurfDTO> allTurfsDTO = allTurfs.stream().map(turf -> this.modelM.map(turf,TurfDTO.class)).collect(Collectors.toList());

		return allTurfsDTO;
	}
}
