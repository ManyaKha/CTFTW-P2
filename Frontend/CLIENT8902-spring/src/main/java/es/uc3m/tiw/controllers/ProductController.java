package es.uc3m.tiw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Product;

@Controller
public class ProductController {
	
	RestTemplate restTemplate;
	
	@RequestMapping (value = "addProduct", method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute Product product) {
	   Product newProduct = restTemplate.postForObject("http://localhost:18903/products", product, Product.class);
	   model.addAttribute("product", newProduct);
	   return "viewProduct";
    }
   
	@RequestMapping (value = "deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(Model model, @RequestParam Product product){
		Product delProduct = restTemplate.getForObject("http://localhost:18903/products/{owner}/{title}", Product.class, product);
		if (delProduct != null) {
			restTemplate.delete("http://localhost:18903/products/{owner}/{title}", delProduct.getOwner(), delProduct.getTitle());
		}
		return "index";	
	}
	

	@RequestMapping (value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(Model model, @ModelAttribute Product product){
		restTemplate.put("http://localhost:18903/products", product, Product.class);
		return "index";	
	}

}
