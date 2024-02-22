package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Occupation;

@Component
public class OccupationDaoService {
	
	private static List<Occupation> occupations =new ArrayList<>();
	private static int occupationCount = 0;
	
	public List<Occupation> findAll(){
		return occupations;
	}

	public Occupation save(Occupation occupation) {
		occupation.setId(++occupationCount);
		occupations.add(occupation);
		return occupation;
	}
	
	public Occupation findOne(int id) {
		Predicate<? super Occupation> predicate = user -> user.getId().equals(id); 
		return occupations.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Occupation> predicate = user -> user.getId().equals(id); 
		occupations.removeIf(predicate);
	}
}
