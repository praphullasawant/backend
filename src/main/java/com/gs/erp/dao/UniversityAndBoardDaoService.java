package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.UniversityAndBoard;

@Component
public class UniversityAndBoardDaoService {

	private static List<UniversityAndBoard> uniandboards = new ArrayList<>();
	private static int uniandboardCount = 0;
	
	
	public List<UniversityAndBoard> findAll(){
		return uniandboards;
	}
	
	public UniversityAndBoard save(UniversityAndBoard uniandboard) {
		uniandboard.setId(++uniandboardCount);
		uniandboards.add(uniandboard);
		return uniandboard;
	}
	
	public UniversityAndBoard findOne(int id) {
		Predicate<? super UniversityAndBoard> predicate = uniandboard -> uniandboard.getId().equals(id);
		return uniandboards.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super UniversityAndBoard> predicate = uniandboard -> uniandboard.getId().equals(id); 
		uniandboards.removeIf(predicate);
	}
}
