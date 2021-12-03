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

import es.uc3m.tiw.domains.User;
import es.uc3m.tiw.domains.UserDAO;

@Controller
public class UserController {
	
	@Autowired
	UserDAO daouser;
	
	@RequestMapping(value= "/users",method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(){
		return daouser.findAll();
	}
	
	@RequestMapping("/users/{email}/{password}")
	public @ResponseBody User getUserByEmailAndPassword(@PathVariable String email,
											  @PathVariable String password){
		return daouser.findByEmailAndPassword(email, password);
	}
	
	@RequestMapping(value= "/user/{email}", method = RequestMethod.GET)
	public @ResponseBody User getUserByEmail (@PathVariable @Validated String email){
		return daouser.findByEmail(email);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/users")
	public @ResponseBody User createUser(@RequestBody @Validated User sUser) {
		return daouser.save(sUser);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/users/{id}")
	public @ResponseBody User updateUser(@PathVariable @Validated Long id, @RequestBody User uUser) {
		User us = daouser.findById(id).orElse(null);
		us.setName(uUser.getName());
		us.setSurname(uUser.getSurname());
		us.setCity(uUser.getCity());
		us.setEmail(uUser.getEmail());
		us.setPassword(uUser.getPassword());
		return daouser.save(us);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/user/{id}")
	public void deleteUser(@PathVariable @Validated Long id)	{
		User us = daouser.findById(id).orElse(null);
		if(us != null) {
			daouser.delete(us);
		}
	}


}
