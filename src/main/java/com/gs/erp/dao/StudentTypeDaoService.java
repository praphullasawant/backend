package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.StudentType;

@Component
public class StudentTypeDaoService {
	
	private static List<StudentType> students =new ArrayList<>();
	private static int typeCount = 0;
	
	public List<StudentType> findAll(){
		return students;
	}

	public StudentType save(StudentType studenttype) {
		studenttype.setId(++typeCount);
		students.add(studenttype);
		return studenttype;
	}
	
	public StudentType findOne(int id) {
		Predicate<? super StudentType> predicate = user -> user.getId().equals(id); 
		return students.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super StudentType> predicate = user -> user.getId().equals(id); 
		students.removeIf(predicate);
	}
}
