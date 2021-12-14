package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.User;

@Controller
public class NavigationController {
	
	@Autowired
	RestTemplate restTemplate;
	String CURRENT_URL = "http://localhost:18902/users/current";
	
	@RequestMapping("/")
	public String index(Model model) {
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		if (current == null) {
			return "login.html";
		} else if (!current.isAdministrator()) {
			return "notAdminErrorPage.html";
		} else {
			User[] users = restTemplate.getForObject("http://localhost:18902/users", User[].class);
			model.addAttribute("users", users);
			return "manageUsers.html";
		}
	}
		
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		if (current != null && current.isAdministrator()) {
			User[] users = restTemplate.getForObject("http://localhost:18902/users", User[].class);
			model.addAttribute("users", users);
			return "manageUsers.html";
		} else {
			return "login.html";
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrerPage(Model model) {
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		if (current == null) {
			return "register.html";
		} else if (current.isAdministrator()) {
			return "manageUsers.html";
		} else {
			return "notAdminErrorPage.html";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(Model model) {
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		if (current == null) {
			return "login.html";
		} else if (current.isAdministrator()) {
			return "manageUsers.html";
		} else {
			return "notAdminErrorPage.html";
		}
	}
}