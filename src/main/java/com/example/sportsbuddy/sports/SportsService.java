package com.example.sportsbuddy.sports;
import com.example.sportsbuddy.user.User;
import org.modelmapper.ModelMapper;
import com.example.sportsbuddy.sports.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SportsService {
	
	private final UserRepository userRepo;
	
	private final SportsRepository sportsRepo;
	
	private final ModelMapper modelMapper;
	
	SportsService(UserRepository userR, SportsRepository sportsR, ModelMapper modelm){
		this.userRepo = userR;
		this.sportsRepo = sportsR;
		this.modelMapper = modelm;
	}
	
	
	public List<SportsDTO>  getAllSports(){
		List<Sports> allSports = sportsRepo.findAll();
		List<SportsDTO> allSportsDTO = allSports.stream().map(sports -> this.modelMapper.map(sports,SportsDTO.class)).collect(
				Collectors.toList());
		return allSportsDTO;
	}
	
	public List<SportsDTO> getSportsByUser(String uid){
		Optional<User> user = this.userRepo.findById(uid);
	List<Sports> allSportsByUser = sportsRepo.findByUser(user);
	List<SportsDTO> allSportsDTO = allSportsByUser.stream().map(sport -> this.modelMapper.map(sport,SportsDTO.class))
			.collect(Collectors.toList());
	
	return allSportsDTO;
	}
	
	public SportsDTO createSports(SportsDTO sportsDTO){
		Sports sport = this.modelMapper.map(sportsDTO,Sports.class);
		System.out.println(sport.toString());
		this.sportsRepo.save(sport);
		
		return this.modelMapper.map(sport,SportsDTO.class);
	}

}
