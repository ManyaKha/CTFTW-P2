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
	
	
	@RequestMapping(value= "/users/{email}", method = RequestMethod.GET)
	public @ResponseBody User getUserByEmail (@PathVariable @Validated String email){
		return daouser.findByEmail(email);
	}
	
	@RequestMapping(value = "/users/{email}/{password}", method = RequestMethod.GET)
	public @ResponseBody User getUserByEmailAndPassword(@PathVariable String email,
											  @PathVariable String password){
		return daouser.findByEmailAndPassword(email, password);
	}

	@RequestMapping(method = RequestMethod.POST, value="/users")
	public @ResponseBody User createUser(@RequestBody @Validated User sUser) {
		System.out.println(sUser);
		return daouser.save(sUser);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/users/{email}")
	public @ResponseBody User updateUser(@PathVariable @Validated String email, @RequestBody User uUser) {
		User us = daouser.findByEmail(email);
		us.setName(uUser.getName());
		us.setSurname(uUser.getSurname());
		us.setCity(uUser.getCity());
		us.setEmail(uUser.getEmail());
		us.setPassword(uUser.getPassword());
		us.setAdministrator(uUser.isAdministrator());
		return daouser.save(us);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/users/{email}")
	public void deleteUser(@PathVariable @Validated String email)	{
		User us = daouser.findByEmail(email);
		if(us != null) {
			daouser.delete(us);
		}
	}
	
	


}
