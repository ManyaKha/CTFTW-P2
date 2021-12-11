package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public String createUser(Model model, @ModelAttribute User user) {
		restTemplate.postForObject(this.CLIE8902_URL, user, User.class);
		return "login.html";
	}
	
	@RequestMapping(value = "/login-user", method = RequestMethod.POST)
	public String loginUser(Model model, @RequestParam String email, @RequestParam String password) {
		User user = restTemplate.getForObject(this.CLIE8902_URL+"/"+email+"/"+password, User.class);
		if (user == null || !user.isAdministrator()){
			model.addAttribute("loginFailed", true);
			return "login.html";
		} else {
			user.setCurrent(true);
			restTemplate.put("http://localhost:18902/users/"+ user.getEmail(), user);
			return "manageUsers.html";
		}
	}
	
	@RequestMapping(value ="/logout-user", method = RequestMethod.GET)
	public String logoutUser(){
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		current.setCurrent(false);
		restTemplate.put(this.CLIE8902_URL + "/" + current.getEmail(), current);
		return "login.html";
	}
	
	@RequestMapping(value = "/manage-users", method = RequestMethod.GET)
	public String showManageUsers(Model model) {
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		
		if (current == null) {
			return "login.html";
		} else if (!current.isAdministrator()) {
			return "notAdminErrorPage.html";
		} else  {
			User[] users = restTemplate.getForObject(this.CLIE8902_URL, User[].class);
			model.addAttribute("users", users);
			return "manageUsers.html";
		}
	}
	
	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String editUser(Model model, User user){
		restTemplate.put(this.CLIE8902_URL+"/"+user.getEmail(), user);
		User[] users = restTemplate.getForObject(this.CLIE8902_URL, User[].class);
		model.addAttribute("users", users);
		return "manageUsers.html";
	}
	
	@RequestMapping(value = "/delete-user/{email}", method = RequestMethod.POST)
	public String deleteUser(Model model, @PathVariable String email) {
		restTemplate.delete(this.CLIE8902_URL+"/"+email);
		return "manageUsers.html";
	}
}
