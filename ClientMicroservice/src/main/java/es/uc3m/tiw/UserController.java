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
	
	@RequestMapping("/users/{email}/{password}")
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
		return daouser.save(us);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/users/{email}")
	public void deleteUser(@PathVariable @Validated String email)	{
		User us = daouser.findByEmail(email);
		if(us != null) {
			daouser.delete(us);
		}
	}
	
	
	
//	@RequestMapping(value="/users", method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<List<User>> getUsers(){
//		List<User> userList = daouser.findAll();
//		return new ResponseEntity<>(userList, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/users/{name}",  method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<User> getUserByEmail(@PathVariable String email){
//		 User user = daouser.findByEmail(email);
//		 return new ResponseEntity<>(user, HttpStatus.OK);
//	}
//	

	
	//Get users by name and surname - with the parameters in the path
//	@RequestMapping(value="/users/{name}/{surname}",  method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<List<User>> getUserByNameAndSurname(@PathVariable String name,
//											  @PathVariable String surname){
//		return new ResponseEntity<>(daouser.findByEmailAndPassword(name, surname), HttpStatus.OK);
//	}
//	
//	//Create a user
//	@RequestMapping(value="/users", method=RequestMethod.POST)
//	public ResponseEntity<User> createUser(@RequestBody User puser){
//		ResponseEntity<User> response;
//		User newUser = daouser.save(puser);
//		if (newUser == null) {
//			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			response = new ResponseEntity<>(newUser, HttpStatus.CREATED);
//		}
//		return response;
//	}
//	
//	// Update a user
//	@RequestMapping(value="/users/{id}", method = RequestMethod.PUT)
//	public @ResponseBody ResponseEntity<User> updateUser(@PathVariable @Validated String email, @RequestBody User pUser) {
//		ResponseEntity<User> response;
//		//Optional<User> us = daous.findById(id);
//		User us = daouser.findById(email);
//		if (us == null) {
//			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			us.setName(pUser.getName());
//			us.setSurname(pUser.getSurname());				
//			response = new ResponseEntity<>(daouser.save(us), HttpStatus.OK);
//		}
//		return response;
//	}
//	
//	// Delete a user
//	@RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
//	public @ResponseBody ResponseEntity<User> deleteUser(@PathVariable @Validated String email) {
//		ResponseEntity<User> response;
//		User us = daouser.findById(email);
//		if (us == null) {
//			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			daouser.delete(us);
//			response = new ResponseEntity<>(HttpStatus.OK);
//		}
//		return response;
//	
//	}


}
