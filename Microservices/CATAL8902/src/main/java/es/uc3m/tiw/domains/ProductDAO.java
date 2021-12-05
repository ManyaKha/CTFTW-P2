package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//@RestResource(path="Products", rel="Products")
public interface ProductDAO extends CrudRepository<Product,Long>{
	

	public List<Product> findAll();
	
	public List<Product> findByTitle(String Title);
}
