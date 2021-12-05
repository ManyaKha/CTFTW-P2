package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,String>{
	

	public List<Product> findAll();
	
	public List<Product> findByTitle(String Title);
}
