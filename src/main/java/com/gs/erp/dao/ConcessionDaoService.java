package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Concession;

@Component
public class ConcessionDaoService {
	
	private static List<Concession> concessions =new ArrayList<>();
	private static int concessionCount = 0;
	
	public List<Concession> findAll(){
		return concessions;
	}

	public Concession save(Concession concession) {
		concession.setId(++concessionCount);
		concessions.add(concession);
		return concession;
	}
	
	public Concession findOne(int id) {
		Predicate<? super Concession> predicate = user -> user.getId().equals(id); 
		return concessions.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Concession> predicate = user -> user.getId().equals(id); 
		concessions.removeIf(predicate);
	}
}
