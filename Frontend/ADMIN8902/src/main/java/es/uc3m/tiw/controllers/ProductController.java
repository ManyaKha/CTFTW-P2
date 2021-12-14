package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.User;

@Controller
public class ProductController {
	
	@Autowired
	RestTemplate restTemplate;
	String CATAL8902_URL = "http://localhost:18903/products";
	String CURRENT_URL = "http://localhost:18902/users/current";

	
	@RequestMapping(value="/manage-products", method = RequestMethod.GET)
	public String showManageProducts(Model model){
		User current = restTemplate.getForObject(this.CURRENT_URL, User.class);
		if (current == null) {
			return "login.html";
		} else if (!current.isAdministrator()) {
			return "notAdminErrorPage.html";
		} else {
			Product[] products = restTemplate.getForObject(this.CATAL8902_URL, Product[].class);
			model.addAttribute("products", products);
			return "manageProducts.html";
		}
	}
	
	@RequestMapping(value = "/edit-product", method = RequestMethod.POST)
	public String editProduct(Model model, Product product){
		restTemplate.put(this.CATAL8902_URL+"/"+product.getId(), product);
		Product[] products = restTemplate.getForObject(this.CATAL8902_URL, Product[].class);
		model.addAttribute("products", products);
		return "manageProducts.html";
	}
	
	@RequestMapping(value = "/delete-product/{id}", method = RequestMethod.POST)
	public String deleteProduct(@PathVariable String id){
		restTemplate.delete(this.CATAL8902_URL+"/"+id);
		return "manageProducts.html";
	}
}
