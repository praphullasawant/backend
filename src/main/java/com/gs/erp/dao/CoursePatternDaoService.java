package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.CoursePattern;

@Component
public class CoursePatternDaoService {
	
	private static List<CoursePattern> patterns =new ArrayList<>();
	private static int patternCount = 0;
	
	public List<CoursePattern> findAll(){
		return patterns;
	}

	public CoursePattern save(CoursePattern pattern) {
		pattern.setId(++patternCount);
		patterns.add(pattern);
		return pattern;
	}
	
	public CoursePattern findOne(int id) {
		Predicate<? super CoursePattern> predicate = user -> user.getId().equals(id); 
		return patterns.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super CoursePattern> predicate = user -> user.getId().equals(id); 
		patterns.removeIf(predicate);
	}
}
