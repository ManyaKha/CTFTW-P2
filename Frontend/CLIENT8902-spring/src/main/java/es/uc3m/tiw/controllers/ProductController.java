package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.User;

@Controller
public class ProductController {
	@Autowired
	RestTemplate restTemplate;
	
	
	/*View product*/
	@RequestMapping (value = "/", method = RequestMethod.GET)
	public String returnAllProducts(Model model) {
		Product[] productList = restTemplate.getForObject("http://localhost:18903/products",Product[].class);
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		model.addAttribute("productList", productList);
		if (user != null) {
			model.addAttribute("loggedin", true);
			return "index.html";
		}
		return "index.html";
	}
	
	/*Search product - Search*/
	@RequestMapping (value = "search", method = RequestMethod.GET)
	public String searchProducts(Model model, @RequestParam String keyword) {
		Product[] searchResults = restTemplate.getForObject("http://localhost:18903/products/{keyword}", Product[].class, keyword);
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchResults", searchResults);
		if (user != null) {
			model.addAttribute("loggedin", true);
			return "allproducts.html";	
		}
		model.addAttribute("loggedin", false);
		return "allproducts.html";	
	}
	
	
	
	
	/*Create New Product*/
	@RequestMapping (value = "addProduct", method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute Product product) {
	   Product newProduct = restTemplate.postForObject("http://localhost:18903/products/createProduct", product, Product.class);
	   model.addAttribute("product", newProduct);
	   return "index";
    }
   
	/*Delete existing product*/
	@RequestMapping (value = "deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(Model model, @RequestParam Product product){
		Product delProduct = restTemplate.getForObject("http://localhost:18903/products/{owner}/{title}", Product.class, product);
		if (delProduct != null) {
			restTemplate.delete("http://localhost:18903/products/{owner}/{title}", delProduct.getOwner(), delProduct.getTitle());
		}
		return "index";	
	}

	/*Update existing product*/
	@RequestMapping (value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(Model model, @ModelAttribute Product product){
		Product updateProduct = restTemplate.getForObject("http://localhost:18903/products/{owner}/{title}", Product.class, product);
		if(updateProduct !=null) {
			restTemplate.put("http://localhost:18903/products/{owner}/{title}", updateProduct.getOwner(), updateProduct.getTitle());			
		}
		return "index";	
	}
	
	
	
}
