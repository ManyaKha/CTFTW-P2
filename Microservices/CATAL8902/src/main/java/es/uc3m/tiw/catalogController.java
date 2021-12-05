package es.uc3m.tiw;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
   @RequestMapping(value = "/index")
   public String index() {
      return "index";
   }
   
  /** @RequestMapping(value="/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Product>> getUsers(@RequestParam(value="Title",required=false) String Title){
		List<Product> productList;
		productList = daoproduct.findAll();
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}**/
   
   @RequestMapping(value= "/products",method = RequestMethod.GET)
	public @ResponseBody List<Product> getUsers(){
		return daoproduct.findAll();
	}
}