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
	

	@RequestMapping (value = "loginUser", method = RequestMethod.GET)
	public String loginUser(Model model, @RequestParam String email, @RequestParam String password) {
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}/{password}", User.class, email, password);
		model.addAttribute("user", user);
		if (user != null) {	
			return "myaccount.html";
		}
		return "login.html";

	}
	
	@RequestMapping (value = "registerUser", method = RequestMethod.POST)
	public String createUser(Model model, @ModelAttribute User us) {
		User user = restTemplate.postForObject("http://localhost:18902/users", us, User.class);
		model.addAttribute("user", user);
		return "myaccount.html";
	}
	
	
	@RequestMapping (value = "deleteUserAccount", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String email){
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}", User.class, email);
		if (user != null) {
			restTemplate.delete("http://localhost:18902/users/{email}", user.getEmail());
		}
		return "index";	
	}
	
	@RequestMapping (value = "updateUserAccount", method = RequestMethod.POST)
	public String updateUser(Model model, @RequestParam String email){
		User user = restTemplate.getForObject("http://localhost:18902/users/{email}", User.class, email);
		if (user != null) {
			restTemplate.put("http://localhost:18902/users/{email}", user.getEmail());
		}
		return "index";	
	}
}
