package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Section;

@Component
public class SectionDaoService {
	
	private static List<Section> sections =new ArrayList<>();
	private static int sectionCount = 0;

	public List<Section> findAll(){
		return sections;
	}

	public Section save(Section section) {
		section.setId(++sectionCount);
		sections.add(section);
		return section;
	}
	
	public Section findOne(int id) {
		Predicate<? super Section> predicate = section -> section.getId().equals(id); 
		return sections.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Section> predicate = section -> section.getId().equals(id); 
		sections.removeIf(predicate);
	}
}
