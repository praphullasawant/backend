package com.gs.erp.repository.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.FeePatternRepository;
import com.gs.erp.models.FeePattern;

import jakarta.validation.Valid;

@RestController
public class FeePatternResource {
	private FeePatternRepository feepatternRepository;
	
	public FeePatternResource(FeePatternRepository feepatternRepository) {
		this.feepatternRepository=feepatternRepository;
	}
	
	@GetMapping("/feepattern")
	public List<FeePattern> retrieveAllFeePattern(){
		Sort sortDesc = Sort.by(Sort.Order.desc("id"));
		return feepatternRepository.findAll(sortDesc);
	}
	
	@GetMapping("/feepattern/{id}")
	public Optional<FeePattern> retrieveFeePattern(@PathVariable int id){
		Optional<FeePattern> feepattern = feepatternRepository.findById(id);	
		if (feepattern==null) {
			throw new UserNotFoundException("id:"+id);
		}	
		return feepattern;
	}
	
	@DeleteMapping("/feepattern/{id}")
	public void deleteFeePattern(@PathVariable int id){
		feepatternRepository.deleteById(id);		
	}
	
	@PostMapping("/feepattern")
	public ResponseEntity<FeePattern> createFeePattern(@Valid @RequestBody FeePattern feepattern) {
		FeePattern savedFeePattern = feepatternRepository.save(feepattern);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedFeePattern.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/feepattern/{id}")
	public ResponseEntity<FeePattern> updateFeePattern(@PathVariable int id, @Valid @RequestBody FeePattern feepattern) {
	    Optional<FeePattern> existingFeePattern = feepatternRepository.findById(id);
	    if (existingFeePattern.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }
	    existingFeePattern.get().setFeePattern(feepattern.getFeePattern()); 
	    existingFeePattern.get().setFeePatternName(feepattern.getFeePatternName()); 
	    existingFeePattern.get().setNoOfSemester(feepattern.getNoOfSemester());
	    FeePattern updatedFeePattern = feepatternRepository.save(existingFeePattern.get());
	    return ResponseEntity.ok().body(updatedFeePattern);
	}
}
