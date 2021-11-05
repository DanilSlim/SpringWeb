package com.habuma.spitter.dao;

import java.util.List;

import com.habuma.spitter.base.Spitter;

public interface SpitterDAO {
	
	 void addSpitter(Spitter spitter);
	 
	 void editSpitter(Spitter spitter);
	 
	 void deleteSpitter(Spitter spitter);
	 
	 List <Spitter> showAllSpitters();
	 
	 List<Spitter> showNumSpitters(int Num);
	 
	 Spitter findSpitterById(Long id);
	 
	 Spitter findSpitterByUserName(String userName);

}
