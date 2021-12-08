package es.uc3m.tiw;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return daoproduct.findByTitle(title);
   }
	
   /*Search Product by Owner*/
   @RequestMapping(value= "/products/users/{owner}",method = RequestMethod.GET)
   public @ResponseBody List<Product> getProductsByOwner(@PathVariable @Validated String owner){
		return daoproduct.findByOwner(owner);
   }
   
   /*Search Product by Title and Owner*/
   @RequestMapping(value= "/products/{title}/{owner}",method = RequestMethod.GET)
   public @ResponseBody Product getProductsByTitleAndOwner(String title, String owner){
		return daoproduct.findByOwnerAndTitle(owner, title);
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
	@RequestMapping(method = RequestMethod.DELETE, value="/products/{owner}/{title}")
	public void deleteProduct(@PathVariable @Validated String title, String owner)	{
		Product prod = daoproduct.findByOwnerAndTitle(owner, title);
		if(prod != null) {
			daoproduct.delete(prod);
		}
	}
	
	/*Update existing product*/
	@RequestMapping(method = RequestMethod.PUT, value="/products/{owner}/{title}")
	public @ResponseBody Product updateProduct(@PathVariable String owner, @PathVariable String title, @RequestBody Product sProd) {
		Product prod = daoproduct.findByOwnerAndTitle(owner, title);
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