package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import com.gs.erp.jparepository.StudentTypeRepository;
import com.gs.erp.models.StudentType;

import jakarta.validation.Valid;

@RestController
public class StudentTypeResource {
	private StudentTypeRepository studentTypeRepository;
	
	public StudentTypeResource(StudentTypeRepository studentTypeRepository) {
		this.studentTypeRepository = studentTypeRepository;
	}

	@GetMapping("/studenttype")
	public List<StudentType> retrieveAllStudent(){
		return studentTypeRepository.findAll();
	}
	
	@GetMapping("/studenttype/{id}")
	public EntityModel<StudentType> retrieveStudent(@PathVariable int id){
		Optional<StudentType> studenttype = studentTypeRepository.findById(id);	
		if (studenttype.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}	
		EntityModel<StudentType> entityModel = EntityModel.of(studenttype.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllStudent());
		entityModel.add(link.withRel("all-students"));
		return entityModel;
	}
	
	@DeleteMapping("/studenttype/{id}")
	public void deleteStudent(@PathVariable int id){
		studentTypeRepository.deleteById(id);		
	}
	
	@PostMapping("/studenttype")
	public ResponseEntity<StudentType> createStudent(@Valid @RequestBody StudentType studenttype) {
		StudentType savedType = studentTypeRepository.save(studenttype);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedType.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
