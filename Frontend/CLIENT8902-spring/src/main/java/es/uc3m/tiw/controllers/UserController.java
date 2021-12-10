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

	//Navigation
	
	@RequestMapping("/")	
	public String index(){
		return "index";
	}
	
	@RequestMapping("/loggedin")	
	public String index_loggedin(){
		return "index-loggedin";
	}
	
	@RequestMapping (value = "/register", method = RequestMethod.GET)
	public String regsiter(){
		return "register.html";
	}
	
	@RequestMapping (value="/login", method = RequestMethod.GET)
	public String showLogin(){
		return "login.html";
	}
	
	@RequestMapping (value="/unsubscribe", method = RequestMethod.GET)
	public String deleteUser(Model model){
		User c = getCurrentUser();
		model.addAttribute("current", c);
		return "unsubscribe.html";
	}
	
	@RequestMapping (value="/account", method = RequestMethod.GET)
	public String account(Model model){
		User c = getCurrentUser();
		model.addAttribute("current", c);
		return "account.html";
	}
	
	@RequestMapping (value="/addproduct", method = RequestMethod.GET)
	public String addProduct(){
		return "addproduct.html";
	}
	
	@RequestMapping (value="/myproducts", method = RequestMethod.GET)
	public String myProducts(){
		return "myproducts.html";
	}
	
	@RequestMapping (value="/myprofile", method = RequestMethod.GET)
	public String myProfile(Model model){
		User c = getCurrentUser();
		model.addAttribute("current", c);
		return "myprofile.html";
	}

	
	//
	
	@RequestMapping (value = "set-current-user", method = RequestMethod.PUT)
	public String setCurrentUser(Model model, @ModelAttribute User uUser) {
		uUser.setCurrent(true);
		restTemplate.put("http://localhost:18902/users/"+ uUser.getEmail(), uUser);
		model.addAttribute("current", uUser);
		return "index-loggedin.html";
	}
	
	@RequestMapping (value = "get-current-user", method = RequestMethod.GET)
	public User getCurrentUser() {
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		return user;
	}

	@RequestMapping (value = "login-user", method = RequestMethod.GET)
	public String loginUser(Model model, @RequestParam String email, @RequestParam String password) {
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}/{password}", User.class, email, password);
		if (user != null) {	
			model.addAttribute("user", user);
			setCurrentUser(model, user);
			return "index-loggedin.html";
		}
		return "index.html";
	}
	
	@RequestMapping (value = "register-user", method = RequestMethod.POST)
	public String createUser(Model model, @ModelAttribute User uUser) {
		uUser.setCurrent(true);
		User user = restTemplate.postForObject("http://localhost:18902/users", uUser, User.class);
		model.addAttribute("user", user);
		setCurrentUser(model, user);
		return "index-loggedin.html";
	}
	
	@RequestMapping (value = "logout-user", method = RequestMethod.PUT)
	public String logoutUser(Model model, @RequestParam User uUser){
		User u = getCurrentUser();
		u.setCurrent(false);
		return "index.html";
	}
	
	@RequestMapping (value = "delete-user", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String email){
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}", User.class, email);
		if (user != null) {
			restTemplate.delete("http://localhost:18902/users/{email}", user.getEmail());
		}
		return "index.html";	
	}
	
	@RequestMapping (value = "update-user", method = RequestMethod.PUT)
	public String updateUser(Model model, @ModelAttribute User uUser){
		restTemplate.put("http://localhost:18902/users/"+ uUser.getEmail(), uUser);
		return "index-loggedin.html";	
	}
}
