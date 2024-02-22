package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.BasicCourseRepository;
import com.gs.erp.models.BasicCourse;

import jakarta.validation.Valid;

@RestController
public class BasicCourseResourse {
	
	BasicCourseRepository basicCourseRepository;
	public BasicCourseResourse(BasicCourseRepository basicCourseRepository) {
		this.basicCourseRepository = basicCourseRepository;
	}

	@GetMapping("/basiccourse")
	public List<BasicCourse> retrieveAllCourse(){
		return basicCourseRepository.findAll();
	}
	
	@GetMapping("/basiccourse/{id}")
	public EntityModel<BasicCourse> retrieveCourse(@PathVariable int id) {
		Optional<BasicCourse> BasicCourse = basicCourseRepository.findById(id);
		if (BasicCourse.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		EntityModel<BasicCourse> entityModel = EntityModel.of(BasicCourse.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllCourse());
		entityModel.add(link.withRel("all-details"));
		return entityModel;
	}
	
	@PostMapping("/basiccourse")
	public ResponseEntity<BasicCourse> createCourse(@Valid @RequestBody BasicCourse basicCourse) {
		BasicCourse savedCourse = basicCourseRepository.save(basicCourse);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCourse.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}

