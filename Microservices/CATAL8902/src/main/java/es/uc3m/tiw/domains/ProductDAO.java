package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,Long>{

	public List<Product> findAll();
	public List<Product> findByTitle(String title);
	public List<Product> findByCategory(String category);
	public List<Product> findByOwner(String owner);
	public List<Product> findByStatus(String status);
	public Product findByOwnerAndTitle(String owner, String title);
	}
