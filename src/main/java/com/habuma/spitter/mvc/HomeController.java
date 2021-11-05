package com.habuma.spitter.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.habuma.spitter.base.Spittle;
import com.habuma.spitter.dao.SpitterDAO;
import com.habuma.spitter.dao.SpittleDAO;

@Controller

public class HomeController {
	
	public static final int DEFAULT_SPITTLES_PER_PAGE=5;
	
	private SpitterDAO spitterDAO;
	
	private SpittleDAO spittleDAO;
	
	

	public SpittleDAO getSpittleDAO() {
		return spittleDAO;
	}

	@Autowired
	public void setSpittleDAO(SpittleDAO spittleDAO) {
		this.spittleDAO = spittleDAO;
	}

	public SpitterDAO getSpitterDAO() {
		return spitterDAO;
	}

	@Autowired
	public void setSpitterDAO(SpitterDAO spitterDAO) {
		this.spitterDAO = spitterDAO;
	}
	
	

	@RequestMapping({"/","/home"})
	public String showHomePage(Model model) {
		
		 List<String> result=new ArrayList<>(HomeController.DEFAULT_SPITTLES_PER_PAGE);
		 
		 String userName=null;
		 
		 List<Spittle> spittles=spittleDAO.showNumSpittle(HomeController.DEFAULT_SPITTLES_PER_PAGE);
		 
		 for(Spittle spittle:spittles) {
			 
			 Long spitter_id=spittle.getSpitter_id();
			 		 
			 userName=spitterDAO.findSpitterById(spitter_id).getUserName();
			 
			 String resultString=userName+" "+spittle.getMessage()+" "+spittle.getTime();
			 
			 result.add(resultString);
		 }
		 
		 model.addAttribute("spittles",result);
		 
		
		
		return "home";
		
		
	}

}
