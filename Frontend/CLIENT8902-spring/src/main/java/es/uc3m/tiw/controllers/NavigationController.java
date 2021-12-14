package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.User;

@Controller
public class NavigationController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")	
	public String index(Model model){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("loggedin", true);
		}
		return "index";
	}
	
	@RequestMapping (value = "/register", method = RequestMethod.GET)
	public String regsiter(Model model){
		return "register.html";
	}
	
	@RequestMapping (value="/login", method = RequestMethod.GET)
	public String showLogin(Model model){
		return "login.html";
	}
	
	@RequestMapping (value="/products", method = RequestMethod.GET)
	public String allProducts(Model model){
		return "allproducts.html";
	}
	
	
	@RequestMapping (value="/myprofile", method = RequestMethod.GET)
	public String myProfile(Model model){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "myprofile.html";
		}
		  
		return "index.html";
		
	}
	
	@RequestMapping (value="/editprofile", method = RequestMethod.GET)
	public String account(Model model){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "editprofile.html";
		}
		  
		return "index.html";
	}
	
	@RequestMapping (value="/deleteprofile", method = RequestMethod.GET)
	public String deleteUser(Model model, @ModelAttribute Product product){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "deleteprofile.html";
		}
		  
		return "index.html";
	}
	
	
	@RequestMapping (value="/addproduct", method = RequestMethod.GET)
	public String addProduct(Model model){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("current", c);
			return "addproduct.html";
		}
		return "index.html";
	}
	
	@RequestMapping (value="/editproduct", method = RequestMethod.GET)
	public String editProduct(Model model, @ModelAttribute Product product){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "editproduct.html";
		}
		return "index.html";
	}
	
	@RequestMapping (value="/deleteproduct", method = RequestMethod.GET)
	public String deleteProduct(Model model, @ModelAttribute Product product){
		User c = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "deleteproduct.html";
		}
		return "index.html";
	}
	

}
