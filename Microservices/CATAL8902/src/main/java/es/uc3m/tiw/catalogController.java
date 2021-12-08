package es.uc3m.tiw;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.ProductDAO;

@Controller
public class catalogController {
	//@Autowired
	//ProductDAO daoproduct;
	
	@Autowired
	ProductDAO daoproduct;
	
	@Autowired
	RestTemplate restTemplate;
	
	/*Main Page*/
   @RequestMapping(value = "/index")
   public String index() {
      return "index";
   }
   
   /*View all Products*/
   @RequestMapping(value= "/products",method = RequestMethod.GET)
	public @ResponseBody List<Product> getAllProducts(){
		return daoproduct.findAll();
	}
   
   /*Search Product by Title*/
   @RequestMapping(value= "/products/{title}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductsByTitle(String title){
		return daoproduct.findByTitle(title);
   }
	
   /*Search Product by Owner*/
   @RequestMapping(value= "/products/{owner}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductsByOwner(String owner){
		return daoproduct.findByOwner(owner);
   }
   
   /*Search Product by Title and Owner*/
   @RequestMapping(value= "/products/{title}/{owner}",method = RequestMethod.GET)
   public @ResponseBody Product getProductsByTitleAndOwner(String title, String owner){
		return daoproduct.findByTitleAndOwner(title,owner);
   }
   
   /*Search Product by Category*/
   @RequestMapping(value= "/products/{category}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductByCategory(String category){
		return daoproduct.findByCategory(category);
   }
  
   
   /*Search Product by Status*/
   @RequestMapping(value= "/products/{status}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductByStatus(String status){
		return daoproduct.findByStatus(status);
   }
   
}