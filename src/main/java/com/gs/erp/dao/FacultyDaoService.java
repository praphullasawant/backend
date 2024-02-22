package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Faculty;

@Component
public class FacultyDaoService {
	
	private static List<Faculty> facultys =new ArrayList<>();
	private static int facultysCount = 0;
	
	public List<Faculty> findAll(){
		return facultys;
	}

	public Faculty save(Faculty faculty) {
		faculty.setId(++facultysCount);
		facultys.add(faculty);
		return faculty;
	}
	
	public Faculty findOne(int id) {
		Predicate<? super Faculty> predicate = faculty -> faculty.getId().equals(id); 
		return facultys.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Faculty> predicate = faculty -> faculty.getId().equals(id); 
		facultys.removeIf(predicate);
	}
}
