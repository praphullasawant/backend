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
import com.gs.erp.jparepository.CertificateRepository;
import com.gs.erp.models.Certificate;

import jakarta.validation.Valid;

@RestController
public class CertificateResource {
	private CertificateRepository certificateRepository;
	
	public CertificateResource(CertificateRepository certificateRepository) {
		this.certificateRepository=certificateRepository;
	}
	
	@GetMapping("/certificate")
	public List<Certificate> retrieveAllCertificate(){
		return certificateRepository.findAll();
	}
	
	@GetMapping("/certificate/{id}")
	public EntityModel<Certificate> retrieveCertificate(@PathVariable int id){
		Optional<Certificate> certificate = certificateRepository.findById(id);
		if (certificate.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<Certificate> entityModel = EntityModel.of(certificate.get());
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllCertificate());
		entityModel.add(link.withRel("all-certifiates"));
		return entityModel;
	}
	
	@DeleteMapping("/certificate/{id}")
	public void deleteCertificate(@PathVariable int id){
		certificateRepository.deleteById(id);		
	}
	
	@PostMapping("/certificate")
	public ResponseEntity<Certificate> createCertificate(@Valid @RequestBody Certificate certificate) {
		Certificate savedCertificate = certificateRepository.save(certificate);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCertificate.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
