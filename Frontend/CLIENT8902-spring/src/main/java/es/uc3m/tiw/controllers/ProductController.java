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

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.User;

@Controller
public class ProductController {
	@Autowired
	RestTemplate restTemplate;

	/* View product */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllProducts(Model model) {
		Product[] productList = restTemplate.getForObject("http://localhost:18903/products", Product[].class);
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		model.addAttribute("productList", productList);
		if (user != null) {
			model.addAttribute("loggedin", true);
			return "index.html";
		}
		return "index.html";
	}

	/* Search product - Search */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchProducts(Model model, @RequestParam String keyword) {
		Product[] searchResults = restTemplate.getForObject("http://localhost:18903/products/{keyword}",
				Product[].class, keyword);
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

	/* Search product - Search by category */
	@RequestMapping(value = "/search-category", method = RequestMethod.GET)
	public String advanceSearchProducts(Model model, @RequestParam String category) {
		Product[] searchResults = restTemplate.getForObject("http://localhost:18903/products/category/{category}",
				Product[].class, category);
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		model.addAttribute("category", category);
		model.addAttribute("searchResults", searchResults);
		if (user != null) {
			model.addAttribute("loggedin", true);
			return "allproducts.html";
		}
		model.addAttribute("loggedin", false);
		return "allproducts.html";
	}

	/* Create New Product */
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute Product product) {
		Product newProduct = restTemplate.postForObject("http://localhost:18903/products", product, Product.class);
		System.out.println(newProduct);
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		model.addAttribute("product", newProduct);
		model.addAttribute("user", user);
		return "index.html";
	}

	/* Get all products by user */
	@RequestMapping(value = "/myproducts", method = RequestMethod.GET)
	public String getAllProductsByUser(Model model) {
		User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		if (user != null) {
			Product[] products = restTemplate
					.getForObject("http://localhost:18903/products/users/" + user.getEmail(), Product[].class);
			model.addAttribute("products", products);
			model.addAttribute("current", user);
			return "myproducts.html";
		}
		return "index.html";
	}

	/* Delete existing product */
	@RequestMapping(value = "/delete-product/{id}", method = RequestMethod.POST)
	public String deleteProduct(@PathVariable String id) {
		restTemplate.delete("http://localhost:18903/products/" + id);
		return "myproducts.html";
	}

	
	  /*Update existing product*/
	  @RequestMapping (value = "/edit-product", method = RequestMethod.POST)
	  public String updateProduct(Model model, Product product){
		  restTemplate.put("http://localhost:18903/products/"+product.getId(), product); 
		  User user = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
		  Product[] products = restTemplate
					.getForObject("http://localhost:18903/products/users/" + user.getEmail(), Product[].class);
		  model.addAttribute("products", products);
		  return "myproducts.html"; 
	  }
	 
}
