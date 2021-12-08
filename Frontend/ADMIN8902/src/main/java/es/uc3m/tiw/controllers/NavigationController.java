package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class NavigationController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
	
	@RequestMapping("/index")
	public String showIndex() {
		return "index.html";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrerPage() {
		return "register.html";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {
		return "login.html";
	}
	
	@RequestMapping(value = "/manage-users", method = RequestMethod.GET)
	public String showManageUsers() {
		return "manageUsers.html";
	}
	
	@RequestMapping(value = "/manage-products", method = RequestMethod.GET)
	public String showManageProducts() {
		return "manageProducts.html";
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String showTransactions() {
		return "transactions.html";
	}
	
	

}
