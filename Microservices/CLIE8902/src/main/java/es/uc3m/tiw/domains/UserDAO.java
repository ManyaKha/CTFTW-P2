package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long>{

	public List<User> findAll();
	
	public User findByEmail(String email); 	
	
	public User findByEmailAndPassword(String email, String password);
	
	@Query("Select u from User u Where u.current=true") 
	public List<User> findCurrentUsers();
	
	
	
	
	
	
}
