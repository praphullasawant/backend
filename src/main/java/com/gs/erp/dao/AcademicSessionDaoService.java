package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.AcademicSession;

@Component
public class AcademicSessionDaoService {
	
	private static List<AcademicSession> sessions =new ArrayList<>();
	private static int sessionsCount = 0;
	
	public List<AcademicSession> findAll(){
		return sessions;
	}

	public AcademicSession save(AcademicSession academicsession) {
		academicsession.setId(++sessionsCount);
		sessions.add(academicsession);
		return academicsession;
	}
	
	public AcademicSession findOne(int id) {
		Predicate<? super AcademicSession> predicate = session -> session.getId().equals(id); 
		return sessions.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super AcademicSession> predicate = session -> session.getId().equals(id); 
		sessions.removeIf(predicate);
	}
}
