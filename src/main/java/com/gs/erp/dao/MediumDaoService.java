package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Medium;

@Component
public class MediumDaoService {
	
	private static List<Medium> mediums =new ArrayList<>();
	private static int mediumCount = 0;

	public List<Medium> findAll(){
		return mediums;
	}

	public Medium save(Medium medium) {
		medium.setId(++mediumCount);
		mediums.add(medium);
		return medium;
	}
	
	public Medium findOne(int id) {
		Predicate<? super Medium> predicate = user -> user.getId().equals(id); 
		return mediums.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Medium> predicate = user -> user.getId().equals(id); 
		mediums.removeIf(predicate);
	}
}
