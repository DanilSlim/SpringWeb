package com.habuma.spitter.mvc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.ui.Model;

import com.habuma.spitter.base.Spittle;
import com.habuma.spitter.dao.SpittleDAOImpl;


public class HomeControllerTest {
	
	@Test
	public void testHomeController() {
		
		List<Spittle> expectedSpittles= new ArrayList<>(3);
		
		for(int i=0;i>2;i++)
			expectedSpittles.add(new Spittle());
		
		
		//SpittleDAOImpl spittleDAO = moc
		
		HomeController controller=new HomeController();
		
		String viewName=controller.showHomePage(null);
		
		assertEquals("home", viewName);
		
	}

}
