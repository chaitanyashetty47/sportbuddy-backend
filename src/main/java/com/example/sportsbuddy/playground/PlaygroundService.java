package com.example.sportsbuddy.playground;

import com.example.sportsbuddy.exceptions.ResourceNotFoundException;
import com.example.sportsbuddy.response.PlaygroundResponse;
import com.example.sportsbuddy.turf.Turf;
import com.example.sportsbuddy.turf.TurfDTO;
import com.example.sportsbuddy.turf.TurfRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaygroundService {
	
	private final PlaygroundRepository pRepository;
	
	private final TurfRepository tRepo;
	private final ModelMapper mmapper;
	
	public PlaygroundService(PlaygroundRepository repo,TurfRepository trepo, ModelMapper mMap){
		this.pRepository = repo;
		this.tRepo = trepo;
		this.mmapper= mMap;
	}
	
	public PlaygroundDTO createPlayground(PlaygroundDTO pgDTO, String id){
	    Turf turf = this.tRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","post id",id));
		
		Playground playground = this.mmapper.map(pgDTO,Playground.class);
		playground.setTurf(turf);
		Playground savedPlayground = this.pRepository.save(playground);
		
		return this.mmapper.map(savedPlayground, PlaygroundDTO.class);
	}
	
	public PlaygroundResponse findAllPlayground(Integer pagenumber, Integer size,String sortBy, String sortOrder){
		Sort sort = (sortOrder.equalsIgnoreCase("asc"))?sort = Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pagenumber,size,sort);
		Page<Playground> page = this.pRepository.findAll(p);
		
		List<Playground> playgrounds = page.getContent();
		List<PlaygroundDTO> pgDto = playgrounds.stream().map((playground) -> this.mmapper.map(playground,PlaygroundDTO.class))
				.collect(Collectors.toList());
		
		PlaygroundResponse pgresponse = new PlaygroundResponse();
		pgresponse.setPlaygrounds(pgDto);
		pgresponse.setPageNumber(page.getNumber());
		pgresponse.setPageSize(page.getSize());
		pgresponse.setTotalElements(page.getTotalElements());
		pgresponse.setTotalPages(page.getTotalPages());
		pgresponse.setLastPage(page.isLast());
		
		
		return pgresponse;
	}
	
	public List<PlaygroundDTO> findPlaygroundByTurf(String turfName, String turfID){
		Turf turf = this.tRepo.findByNameandId(turfName, turfID);
		List<Playground> pgs = this.pRepository.findByTurf(turf);
		
		List<PlaygroundDTO> pgsDTO = pgs.stream().map((pg) -> this.mmapper.map(pg, PlaygroundDTO.class))
				.collect(Collectors.toList());
		return pgsDTO;
	}
}
