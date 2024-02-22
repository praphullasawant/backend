package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Certificate;

@Component
public class CertificateDaoService {
	
	private static List<Certificate> certificates =new ArrayList<>();
	private static int certificateCount = 0;
	
	public List<Certificate> findAll(){
		return certificates;
	}

	public Certificate save(Certificate certificate) {
		certificate.setId(++certificateCount);
		certificates.add(certificate);
		return certificate;
	}
	
	public Certificate findOne(int id) {
		Predicate<? super Certificate> predicate = certificate -> certificate.getId().equals(id); 
		return certificates.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Certificate> predicate = certificate -> certificate.getId().equals(id); 
		certificates.removeIf(predicate);
	}
}
