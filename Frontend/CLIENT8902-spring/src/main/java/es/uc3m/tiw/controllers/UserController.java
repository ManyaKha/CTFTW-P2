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
	

	@RequestMapping (value = "set-current-user", method = RequestMethod.PUT)
	public String setCurrentUser(Model model, @ModelAttribute User uUser) {
		uUser.setCurrent(true);
		restTemplate.put("http://localhost:18902/users/"+ uUser.getEmail(), uUser);
		model.addAttribute("current", uUser);
		return "index-loggedin.html";
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
	public void logoutUser(@RequestParam User uUser){
		uUser.setCurrent(false);
	}
	
	
	@RequestMapping (value = "delete-user", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String email){
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}", User.class, email);
		if (user != null) {
			restTemplate.delete("http://localhost:18902/users/{email}", user.getEmail());
		}
		return "index.html";	
	}
	
	
}
