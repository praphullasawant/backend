package com.gs.erp.repository.controller;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.UniversityAndBoardRepository;
import com.gs.erp.models.UniversityAndBoard;

import jakarta.validation.Valid;

@RestController
public class UniversityAndBoardResource {
	private UniversityAndBoardRepository universityAndBoardRepository;
	
	public UniversityAndBoardResource(UniversityAndBoardRepository universityAndBoardRepository) {
		this.universityAndBoardRepository = universityAndBoardRepository;
	}

	@GetMapping("/universityandboard")
	public List<UniversityAndBoard> retrieveAllUniversityAndBoard(){
		return universityAndBoardRepository.findAll();
	}
	
	@GetMapping("/universityandboard/{id}")
	public EntityModel<UniversityAndBoard> retrieveTitle(@PathVariable int id){
		Optional<UniversityAndBoard> universityandboard = universityAndBoardRepository.findById(id);	
		if (universityandboard.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<UniversityAndBoard> entityModel = EntityModel.of(universityandboard.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUniversityAndBoard());
		entityModel.add(link.withRel("all-universityandboards"));
		
		return entityModel;
	}

	@DeleteMapping("/universityandboard/{id}")
	public void deleteTitle(@PathVariable int id){
		universityAndBoardRepository.deleteById(id);	
	}
	
	@PostMapping("/universityandboard")
	public ResponseEntity<UniversityAndBoard> createUniversityAndBoard(@Valid @RequestBody UniversityAndBoard universityandboard) {
		UniversityAndBoard savedUniversityAndBoard = universityAndBoardRepository.save(universityandboard);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUniversityAndBoard.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
