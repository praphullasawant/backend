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
import com.gs.erp.jparepository.SemesterRepository;
import com.gs.erp.jparepository.YearRepository;
import com.gs.erp.models.Semester;
import com.gs.erp.models.Year;

import jakarta.validation.Valid;

@RestController
public class YearResource {
	
	private YearRepository yearRepository;
	private SemesterRepository semesterRepository;

	public YearResource(YearRepository yearRepository, SemesterRepository semesterRepository) {
		this.yearRepository = yearRepository;
		this.semesterRepository = semesterRepository;
	}

	@GetMapping("/year")
	public List<Year> retrieveAllYear(){
		return yearRepository.findAll();
	}
	
	@GetMapping("/year/{id}")
	public EntityModel<Year> retrieveBank(@PathVariable int id){
		Optional<Year> year = yearRepository.findById(id);
		if(year.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<Year> entityModel = EntityModel.of(year.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllYear());
		entityModel.add(link.withRel("all-years"));
		return entityModel;
	}
	
	@DeleteMapping("/year/{id}")
	public void deleteYear(@PathVariable int id) {
		yearRepository.deleteById(id);
	}
	
	@PostMapping("/year")
	public ResponseEntity<Year> createYear(@Valid @RequestBody Year year){
		Year savedYear = yearRepository.save(year);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedYear.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/year/{id}/semester")
	public List<Semester> retriveSemesterForYear(@PathVariable int id){
		Optional<Year> year = yearRepository.findById(id);
		if(year.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		return year.get().getSemester();
	}
	
	@PostMapping("/year/{id}/semester")
	public ResponseEntity<Object> createSemesterForYear(@PathVariable int id, @Valid @RequestBody Semester semester){
		Optional<Year> year = yearRepository.findById(id);
		if(year.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		semester.setYear(year.get());
		Semester savedSemester = semesterRepository.save(semester);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSemester.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
