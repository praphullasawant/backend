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
import com.gs.erp.jparepository.OccupationRepository;
import com.gs.erp.models.Occupation;

import jakarta.validation.Valid;

@RestController
public class OccupationResource {
	private OccupationRepository ocupationRepository;

	public OccupationResource(OccupationRepository ocupationRepository) {
		this.ocupationRepository = ocupationRepository;
	}

	@GetMapping("/occupation")
	public List<Occupation> retrieveAllOccupation() {
		return ocupationRepository.findAll();
	}

	@GetMapping("/occupation/{id}")
	public EntityModel<Occupation> retrieveOccupation(@PathVariable int id) {
		Optional<Occupation> occupation = ocupationRepository.findById(id);
		if (occupation.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		EntityModel<Occupation> entityModel = EntityModel.of(occupation.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllOccupation());
		entityModel.add(link.withRel("all-occupation"));

		return entityModel;
	}

	@DeleteMapping("/occupation/{id}")
	public void deleteOccupation(@PathVariable int id) {
		ocupationRepository.deleteById(id);
	}

	@PostMapping("/occupation")
	public ResponseEntity<Occupation> createOcupation(@Valid @RequestBody Occupation occupation) {
		Occupation savedOccupation = ocupationRepository.save(occupation);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedOccupation.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
