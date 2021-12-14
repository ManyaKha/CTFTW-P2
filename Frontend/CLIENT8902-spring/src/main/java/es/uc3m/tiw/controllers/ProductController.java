package es.uc3m.tiw.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.CreditCard;
import es.uc3m.tiw.domains.Payment;
import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.Transaction;
import es.uc3m.tiw.domains.User;

@Controller
public class ProductController {
	@Autowired
	RestTemplate restTemplate;
	
	// LLAMADAS AL CONTROLADOR
	/*
	 * @RequestMapping (value = "viewProducts", method = RequestMethod.GET) public
	 * String viewProducts(Model model, @PathVariable Product products) { Product
	 * pro = restTemplate.getForObject("http://localhost:18903/products",
	 * Product.class, products); model.addAttribute("product", pro); return "index";
	 * }
	 */
	
	/*View Product*/
	@RequestMapping (value = "products", method = RequestMethod.GET)
	public String returnAllProducts(Model model) {
		Product[] prolist = restTemplate.getForObject("http://localhost:18903/products",Product[].class);
		System.out.println("enter in returnAllProducts");
		model.addAttribute("productList", prolist);
		return "index";
	}
	
	/*
	 * @RequestMapping (value = "showAllProducts", method = RequestMethod.POST)
	 * public String saveProducts(Model model, @ModelAttribute Product pro) {
	 * Product Produ =
	 * restTemplate.postForObject("http://localhost:8082/products/productList", pro,
	 * Product.class); model.addAttribute("product", pro); return "index"; }
	 */
	
	
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
	
	/*Search Product- Quick Search*/
	@RequestMapping (value = "searchProduct", method = RequestMethod.POST)
	public String searchUsuarios(Model model, @RequestParam Product product) {
		Product[] searchProduct = restTemplate.getForObject("http://localhost:18903/products/{title}", Product[].class, product);
		product.setTitle("Product1");
		model.addAttribute("searchProduct", searchProduct);
		model.addAttribute("product", product);
		return "index";	
	}
	
	@RequestMapping (value = "product/{id}", method = RequestMethod.GET)
	public String showProduct(Model model, @PathVariable String id) {
		Product product = restTemplate.getForObject("http://localhost:18903/product/"+id, Product.class);
		model.addAttribute("product", product);
		return "product.html";	
	}
	
	@RequestMapping (value="buy-product/{id}", method = RequestMethod.POST)
	public String buyProduct(Model model, @PathVariable String id, 
			@RequestParam String cardNumber,
			@RequestParam String month,
			@RequestParam String year,
			@RequestParam String cv2) {
		
		Product product = restTemplate.getForObject("http://localhost:18903/product/"+id, Product.class);
		CreditCard card = new CreditCard(cardNumber, year+"-"+month, cv2);
		Payment payment = new Payment(product.getPrice(), card);		
		try {
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:18901/payment", payment, String.class);
				Transaction transaction = new Transaction();
				transaction.setTransactionId(response.getBody());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				transaction.setTimeOfTransaction(sdf.format(new Date()));
				transaction.setProductName(product.getTitle());
				transaction.setPrice(product.getPrice());
				transaction.setSeller(product.getOwner());
				
				User current = restTemplate.getForObject("http://localhost:18902/users/current", User.class);
				transaction.setBuyer(current.getEmail());
				
				restTemplate.postForObject("http://localhost:18904/transactions", transaction, void.class);
				model.addAttribute("success", true); 
				
				product.setStatus("sold");
				restTemplate.put("http://localhost:18903/products/"+product.getId(), product);
				
				model.addAttribute("product", product);

				
				return "product.html";
	    } catch(HttpStatusCodeException e) {
	    	System.out.println(e.getStatusCode()+": "+e.getResponseBodyAsString());
	    	model.addAttribute("failed", true);
			model.addAttribute("product", product);
	        return "product.html";
	    }
	}

	
	
}
