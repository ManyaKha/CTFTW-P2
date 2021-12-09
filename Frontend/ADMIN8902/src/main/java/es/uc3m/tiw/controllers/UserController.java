package es.uc3m.tiw.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	String CLIE8902_URL = "http://localhost:18902/";
	
	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public String createUser(Model model, @ModelAttribute User user) {
		restTemplate.postForObject(this.CLIE8902_URL+"users", user, User.class);
		return "login.html";
	}
	
	@RequestMapping(value = "/login-user", method = RequestMethod.GET)
	public String loginUser(Model model, @RequestParam String email, @RequestParam String password) {
		User user = restTemplate.getForObject(this.CLIE8902_URL+"users/"+email+"/"+password, User.class);
		
		if (user == null || !user.isAdministrator()){
			model.addAttribute("loginFailed", true);
			return "login.html";
		} else {
			model.addAttribute("loginFailed", false);
			return "index.html";
		}
	}
	
	@RequestMapping(value = "/manage-users", method = RequestMethod.GET)
	public String showManageUsers(Model model) {
		User[] users = restTemplate.getForObject(this.CLIE8902_URL+"users", User[].class);
		model.addAttribute("users", users);
		return "manageUsers.html";
	}
	
	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String editUser(Model model, User user){
		restTemplate.put(this.CLIE8902_URL+"/users/"+user.getEmail(), user);
		String redirect = this.showManageUsers(model);
		return redirect;
	}
}
