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
	public String index(){
		return "index";
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
	public String deleteUser(){
		return "unsubscribe.html";
	}
	
	@RequestMapping (value="/deleteproduct", method = RequestMethod.GET)
	public String deleteProduct(){
		return "deleteproduct.html";
	}

	@RequestMapping (value = "/updateproduct", method = RequestMethod.GET)
	public String updateProduct(){
		return "updateproduct.html";
	}


}
