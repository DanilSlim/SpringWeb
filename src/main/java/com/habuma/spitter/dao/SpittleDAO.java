package com.habuma.spitter.dao;

import java.util.List;

import com.habuma.spitter.base.Spitter;
import com.habuma.spitter.base.Spittle;

public interface SpittleDAO {
	
	void addSpittle(Spittle spittle);
	
	void editSpittle(Spittle spittle);
	
	void deleteSpittle(Spittle spittle);
	
	List<Spittle>showAllSpittle();
	
	List<Spittle> showNumSpittle(int num);
	
	Spittle findSpittleById(Long id);
	
	Spittle findSpittleByMessage (String message);
	
	List<Spittle> findSpittlesBySpitter(Spitter spitter);

}
