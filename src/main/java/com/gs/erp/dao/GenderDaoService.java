package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Gender;

@Component
public class GenderDaoService {

	private static List<Gender> genders = new ArrayList<>();
	private static int genderCount = 0;
	
	
	public List<Gender> findAll(){
		return genders;
	}
	
	public Gender save(Gender gender) {
		gender.setId(++genderCount);
		genders.add(gender);
		return gender;
	}
	
	public Gender findOne(int id) {
		Predicate<? super Gender> predicate = gender -> gender.getId().equals(id);
		return genders.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Gender> predicate = gender -> gender.getId().equals(id); 
		genders.removeIf(predicate);
	}
}
