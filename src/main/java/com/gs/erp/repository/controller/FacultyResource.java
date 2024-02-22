package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import com.gs.erp.jparepository.FacultyRepository;
import com.gs.erp.models.Faculty;

import jakarta.validation.Valid;

@RestController
public class FacultyResource {
	private FacultyRepository facultyRepository;
	
	public FacultyResource(FacultyRepository facultyRepository) {
		this.facultyRepository=facultyRepository;
	}
	
	@GetMapping("/faculty")
	public List<Faculty> retrieveAllFaculty(){
		Sort soreDesc = Sort.by(Sort.Order.desc("id"));
		return facultyRepository.findAll(soreDesc);
	}
	
	@GetMapping("/faculty/{id}")
	public EntityModel<Faculty> retrieveFaculty(@PathVariable int id) {
	    Optional<Faculty> Faculty = facultyRepository.findById(id);

	    if (Faculty.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }

	    EntityModel<Faculty> entityModel = EntityModel.of(Faculty.get());

	    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllFaculty());
	    entityModel.add(link.withRel("all-faculty"));

	    return entityModel;
	}
	
	@DeleteMapping("/faculty/{id}")
	public void deleteFaculty(@PathVariable int id){
		facultyRepository.deleteById(id);		
	}
	
	@PostMapping("/faculty")
	public ResponseEntity<Faculty> createFaculty(@Valid @RequestBody Faculty faculty) {
		Faculty savedFaculty = facultyRepository.save(faculty);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedFaculty.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/faculty/{id}")
	public ResponseEntity<Faculty> updateDegree(@PathVariable int id, @Valid @RequestBody Faculty faculty) {
	    Optional<Faculty> existingFaculty = facultyRepository.findById(id);
	    if (existingFaculty.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }
	    existingFaculty.get().setFacultyStream(faculty.getFacultyStream());  
	    existingFaculty.get().setCheckIfActive(faculty.getCheckIfActive());
	    Faculty updatedDegree = facultyRepository.save(existingFaculty.get());
	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	    return ResponseEntity.ok().body(updatedDegree);
	}
}
