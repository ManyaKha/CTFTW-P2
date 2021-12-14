package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.User;

@Controller
public class UserController {
	
	@Autowired
	RestTemplate restTemplate;
	String CLIE8902_URL = "http://localhost:18902/users";
	String CURRENT_URL = "http://localhost:18902/users/current";

	//Navigation
	
	@RequestMapping("/")	
	public String index(Model model){
		User c = getCurrentUser();
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
	
	@RequestMapping (value="/myprofile", method = RequestMethod.GET)
	public String myProfile(Model model){
		User c = getCurrentUser();
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "myprofile.html";
		}
		  
		return "index.html";
		
	}
	
	@RequestMapping (value="/editprofile", method = RequestMethod.GET)
	public String account(Model model){
		User c = getCurrentUser();
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "editprofile.html";
		}
		  
		return "index.html";
	}
	
	@RequestMapping (value="/deleteprofile", method = RequestMethod.GET)
	public String deleteUser(Model model){
		User c = getCurrentUser();
		if(c!= null) {
			model.addAttribute("current", c);
			model.addAttribute("loggedin", true);
			return "deleteprofile.html";
		}
		  
		return "index.html";
	}
	
	
	@RequestMapping (value="/addproduct", method = RequestMethod.GET)
	public String addProduct(Model model){
		User c = getCurrentUser();
		if(c!= null) {
			model.addAttribute("current", c);
			return "addproduct.html";
			
		}
		  
		return "index.html";
	}
	
	@RequestMapping (value="/myproducts", method = RequestMethod.GET)
	public String myProducts(Model model){
		User c = getCurrentUser();
		if(c!= null) {
			model.addAttribute("current", c);
			return "myproducts.html";
		}
		  
		return "index.html";
	}
	
	

	
	@RequestMapping (value = "/get-current-user", method = RequestMethod.GET)
	public User getCurrentUser() {
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		return user;
	}

	
	@RequestMapping (value = "/set-current-user", method = RequestMethod.PUT)
	public String setCurrentUser(Model model, @ModelAttribute User uUser) {
		uUser.setCurrent(true);
		restTemplate.put("http://localhost:18902/users/"+ uUser.getEmail(), uUser);
		model.addAttribute("current", uUser);
		model.addAttribute("loggedin", true);
		return "index.html";
	}
	
	
	@RequestMapping (value = "/login-user", method = RequestMethod.GET)
	public String loginUser(Model model, @RequestParam String email, @RequestParam String password) {
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}/{password}", User.class, email, password);
		if (user != null) {	
			model.addAttribute("user", user);
			setCurrentUser(model, user);
			model.addAttribute("loggedin", true);
			return "index.html";
		}
		  
		return "index.html";
	}
	

	@RequestMapping (value = "/register-user", method = RequestMethod.POST)
	public String createUser(Model model, @ModelAttribute User uUser) {
		uUser.setCurrent(true);
		User user = restTemplate.postForObject("http://localhost:18902/users", uUser, User.class);
		model.addAttribute("user", user);
		setCurrentUser(model, user);
		model.addAttribute("loggedin", true);
		return "index.html";
	}
	
	@RequestMapping (value = "/logout-user", method = RequestMethod.GET)
	public String logoutUser(Model model){
		User c = getCurrentUser();
		c.setCurrent(false);
		restTemplate.put(this.CLIE8902_URL + "/" + c.getEmail(), c);
		  
		return "index.html";
	}
	
	
	@RequestMapping (value = "/delete-user", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String email){
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}", User.class, email);
		if (user != null) {
			restTemplate.delete("http://localhost:18902/users/{email}", user.getEmail());
		}
		  
		return "index.html";	
	}
	
	@RequestMapping (value = "/update-user", method = RequestMethod.POST)
	public String updateUser(Model model, @ModelAttribute User uUser){
		User u = getCurrentUser();
		User user = restTemplate.getForObject("http://localhost:18902/users/"+ u.getEmail(), User.class, uUser);
		if (user != null) {
			uUser.setCurrent(true);
			restTemplate.put("http://localhost:18902/users/"+ user.getEmail(), uUser);
			model.addAttribute("loggedin", true);
			return "index.html";
		}
		  
		return "editprofile.html";	
		
	}
	
}
