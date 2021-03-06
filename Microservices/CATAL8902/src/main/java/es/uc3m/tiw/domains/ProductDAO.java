package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductDAO extends CrudRepository<Product,Long>{

	public Product findById(String id);
	public List<Product> findAll();
	@Query(value= "select * from product p where p.title like %?1%", nativeQuery=true)
	public List<Product> findByTitle(String title);
	public List<Product> findByCategory(String category);
	public List<Product> findByOwner(String owner);
	public List<Product> findByStatus(String status);
	}
