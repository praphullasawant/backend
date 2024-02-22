package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Handicap;

@Component
public class HandicapDaoService {

	private static List<Handicap> handicaps = new ArrayList<>();
	private static int handicapCount = 0;

	public List<Handicap> findAll() {
		return handicaps;
	}

	public Handicap save(Handicap handicap) {
		handicap.setId(++handicapCount);
		handicaps.add(handicap);
		return handicap;
	}

	public Handicap findOne(int id) {
		Predicate<? super Handicap> predicate = handicap -> handicap.getId().equals(id);
		return handicaps.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(int id) {
		Predicate<? super Handicap> predicate = handicap -> handicap.getId().equals(id);
		handicaps.removeIf(predicate);
	}
}
