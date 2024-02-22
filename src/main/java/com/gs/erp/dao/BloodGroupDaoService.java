package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.BloodGroup;

@Component
public class BloodGroupDaoService {

	private static List<BloodGroup> bloodgroups = new ArrayList<>();
	private static int bloodgroupCount = 0;
	
	
	public List<BloodGroup> findAll(){
		return bloodgroups;
	}
	
	public BloodGroup save(BloodGroup bloodgroup) {
		bloodgroup.setId(++bloodgroupCount);
		bloodgroups.add(bloodgroup);
		return bloodgroup;
	}
	
	public BloodGroup findOne(int id) {
		Predicate<? super BloodGroup> predicate = bloodgroup -> bloodgroup.getId().equals(id);
		return bloodgroups.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super BloodGroup> predicate = bloodgroup -> bloodgroup.getId().equals(id); 
		bloodgroups.removeIf(predicate);
	}
}
