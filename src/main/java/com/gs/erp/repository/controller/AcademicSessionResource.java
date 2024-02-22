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
import com.gs.erp.jparepository.AcademicSessionRepository;
import com.gs.erp.models.AcademicSession;

import jakarta.validation.Valid;

@RestController
public class AcademicSessionResource {
	private AcademicSessionRepository academicSessionRepository;

	public AcademicSessionResource(AcademicSessionRepository academicSessionRepository) {
		this.academicSessionRepository = academicSessionRepository;
	}

	@GetMapping("/academicsession")
	public List<AcademicSession> retrieveAllSession() {
		Sort sortByDescendingOrder = Sort.by(Sort.Order.desc("id"));
		return academicSessionRepository.findAll(sortByDescendingOrder);
	}

	@GetMapping("/academicsession/{id}")
	public EntityModel<AcademicSession> retrieveSession(@PathVariable int id) {
		Optional<AcademicSession> academicsession = academicSessionRepository.findById(id);
		if (academicsession.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		EntityModel<AcademicSession> entityModel = EntityModel.of(academicsession.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllSession());
		entityModel.add(link.withRel("all-sessions"));
		return entityModel;
	}

	@DeleteMapping("/academicsession/{id}")
	public void deleteSession(@PathVariable int id) {
		academicSessionRepository.deleteById(id);
	}

	@PostMapping("/academicsession")
	public ResponseEntity<AcademicSession> createSession(@Valid @RequestBody AcademicSession academicsession) {
		AcademicSession savedSession = academicSessionRepository.save(academicsession);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedSession.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/academicsession/{id}")
	public ResponseEntity<AcademicSession> updateAcademicSession(@PathVariable int id, @Valid @RequestBody AcademicSession academicsession) {
	    Optional<AcademicSession> existingAcademicSession = academicSessionRepository.findById(id);
	    if (existingAcademicSession.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }
	    existingAcademicSession.get().setSessionName(academicsession.getSessionName()); 
	    existingAcademicSession.get().setShortName(academicsession.getShortName()); 
	    existingAcademicSession.get().setStartDate(academicsession.getStartDate());
	    existingAcademicSession.get().setEndDate(academicsession.getEndDate());
	    existingAcademicSession.get().setActive(academicsession.getActive());
	    existingAcademicSession.get().setOldSession(academicsession.getOldSession());
	    AcademicSession updatedAcademicSession = academicSessionRepository.save(existingAcademicSession.get());
	    //URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	    return ResponseEntity.ok().body(updatedAcademicSession);
	}
}
