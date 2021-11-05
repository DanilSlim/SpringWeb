package com.habuma.spitter.mvc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.habuma.spitter.base.Spitter;
import com.habuma.spitter.base.Spittle;
import com.habuma.spitter.dao.SpitterDAO;
import com.habuma.spitter.dao.SpittleDAO;
import com.habuma.spitter.exception.ImageUploadException;

@Controller
@RequestMapping("/spitters")
public class SpitterController {
	
	private SpitterDAO spitterDAO;
	
	private SpittleDAO spittleDAO;
	
	@Autowired
	public SpitterController(SpitterDAO spitterDAO, SpittleDAO spittleDAO) {
		this.spitterDAO=spitterDAO;
		
		this.spittleDAO=spittleDAO;
	}
	
	
	@GetMapping("/spittles")
	public String listSpittlesForSpitter(@RequestParam( value="spitter", required = false) String userName, Model model) {
		
		if(userName==null) return "redirect:/home";
			
		Spitter spitter =this.spitterDAO.findSpitterByUserName(userName);
		
		if(spitter==null) return "redirect:/home";
		
		List <Spittle> spittles=spittleDAO.findSpittlesBySpitter(spitter);
		
		String spitterName= "Spittles for "+spitter.getUserName();
		
		model.addAttribute("spitter", spitterName);
		
		model.addAttribute("spittles", spittles);
		
		return "/spittles/list";
	}
	
	@GetMapping(params = "new")
	public String createSpitterProfile(Model model) {
		
		model.addAttribute("spitter",new Spitter());
		
		return "spitters/edit";
	}
	
	@PostMapping
	public String createSpitter(@ModelAttribute("spitter") @Valid  Spitter spitter, BindingResult bindingResult,
			                    @RequestParam(value ="image", required = false) MultipartFile image, 
			                    HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) return "spitters/edit";
		
		spitterDAO.addSpitter(spitter);
		
		if(!image.isEmpty()) {
			
			String ext=validateImage(image);
			
			String name=spitter.getId().toString()+ext;
			
			String fullPath= request.getServletContext().getRealPath("")+"/resources/" + name;
			
			saveImage(fullPath, image);
		}
		
		return "redirect:spitters/"+spitter.getUserName();
		
		
	}
	
	@GetMapping("{userName}")
	public String showSpitterProfile(@PathVariable String userName, Model model) {
		
		Spitter spitter=spitterDAO.findSpitterByUserName(userName);
		
		model.addAttribute("spitter",spitter);
		
		return "spitters/view";
	}
	
	
	@GetMapping("/upload")
	public String showUploadFileForm() {
		
		return "spitters/upload";
		
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam(value = "user-file", required = true) MultipartFile file, 
			HttpServletRequest request) {
		
		//if(!file.getContentType().equals("image/jpeg")&&!file.getContentType().equals("image/png")) System.out.println("Not JPEG");
		validateImage(file);
		
		String name=file.getOriginalFilename();
		
		String fullPath= request.getServletContext().getRealPath("")+"/resources/" + name;
		
		System.out.println("Path: "+fullPath);
		
		/*File uploadFile=new File(fullPath);
		
		try {
			FileUtils.writeByteArrayToFile(uploadFile, file.getBytes());
		} catch (IOException e) {
			
			throw new ImageUploadException("Unable to save file");
		}*/
		
		saveImage(fullPath, file);
		
		
		
		
		
		return "redirect:/home";
	}
	
	
	
	private String validateImage(MultipartFile file) throws ImageUploadException{
		
		if(file.getContentType().equals("image/jpeg")) return ".jpg";
		
		else if (file.getContentType().equals("image/png")) return ".png";
		
		else throw new ImageUploadException("File must be jpeg or png only!");	
	}
	
	
	private void saveImage(String fullPath, MultipartFile file ) throws ImageUploadException {
		
		File uploadFile=new File(fullPath);
		
		try {
			FileUtils.writeByteArrayToFile(uploadFile, file.getBytes());
		} catch (IOException e) {
			throw new ImageUploadException("Unable to save image");
		}
		
	}

}
