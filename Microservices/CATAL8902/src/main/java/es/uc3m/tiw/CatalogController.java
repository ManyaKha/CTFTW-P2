package es.uc3m.tiw;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.ProductDAO;

@Controller
public class CatalogController {
	
	@Autowired
	ProductDAO daoproduct;
	
   
	
	
   /*View all Products*/
   @RequestMapping(value= "/products",method = RequestMethod.GET)
	public @ResponseBody List<Product> getAllProducts(){
		return daoproduct.findAll();
	}
   
   /*Search Product by Title*/
   @RequestMapping(value= "/products/{title}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductsByTitle(@PathVariable @Validated String title){
	   if(title != null) {
		 return daoproduct.findByTitle(title);
	   }
	   return daoproduct.findAll();
   }
   
	
   /*Search Product by Owner*/
   @RequestMapping(value= "/products/users/{owner}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductsByOwner(@PathVariable @Validated String owner){
		return daoproduct.findByOwner(owner);
   }
   
   /*Search Product by Id*/
   @RequestMapping(value= "/product/{id}",method = RequestMethod.GET)
   public @ResponseBody Product getProductsId(@PathVariable @Validated String id){
		return daoproduct.findById(id);
   }
   
   /*Search Product by Category*/
   @RequestMapping(value= "/products/category/{category}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductByCategory(@PathVariable @Validated String category){
		return daoproduct.findByCategory(category);
   }
  
   
   /*Search Product by Status*/
   @RequestMapping(value= "/products/status/{status}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductByStatus(@PathVariable @Validated String status){
		return daoproduct.findByStatus(status);
   }
   
   /*Create New Product*/
	@RequestMapping(method = RequestMethod.POST, value="/products")
	public @ResponseBody Product createNewProduct(@RequestBody @Validated Product sProduct) {
		return daoproduct.save(sProduct);
	}
	
	/*Delete Existing Product*/
	@RequestMapping(method = RequestMethod.DELETE, value="/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable @Validated String id)	{
		Product prod = daoproduct.findById(id);
		System.out.println("Catalog");
		System.out.println(prod);
		if(prod != null) {
			daoproduct.delete(prod);
			return new ResponseEntity<>(prod, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/*Update existing product*/
	@RequestMapping(method = RequestMethod.PUT, value="/products/{id}")
	public @ResponseBody Product updateProduct(@PathVariable String id, @RequestBody Product sProd) {
		Product prod = daoproduct.findById(id);
		prod.setId(id);
		prod.setTitle(sProd.getTitle());
		prod.setOwner(sProd.getOwner());	
		prod.setStatus(sProd.getStatus());
		prod.setCategory(sProd.getCategory());
		prod.setDescription(sProd.getDescription());
		prod.setPrice(sProd.getPrice());
		prod.setStatus(sProd.getStatus());
		return daoproduct.save(prod);
	}
}