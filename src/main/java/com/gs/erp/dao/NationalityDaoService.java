package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Nationality;

@Component
public class NationalityDaoService {

	private static List<Nationality> nationalities = new ArrayList<>();
	private static int nationalityCount = 0;
	
	
	public List<Nationality> findAll(){
		return nationalities;
	}
	
	public Nationality save(Nationality nationality) {
		nationality.setId(++nationalityCount);
		nationalities.add(nationality);
		return nationality;
	}
	
	public Nationality findOne(int id) {
		Predicate<? super Nationality> predicate = nationality -> nationality.getId().equals(id);
		return nationalities.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Nationality> predicate = nationality -> nationality.getId().equals(id); 
		nationalities.removeIf(predicate);
	}
}
