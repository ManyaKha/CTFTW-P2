/*
 * package es.uc3m.tiw.controllers;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.client.RestTemplate;
 * 
 * @Controller public class NavigationController {
 * 
 * @Autowired RestTemplate restTemplate;
 * 
 * 
 * @RequestMapping("/") public String index(){ return "index"; }
 * 
 * @RequestMapping("/loggedin") public String index_loggedin(){ return
 * "index-loggedin"; }
 * 
 * @RequestMapping (value = "/register", method = RequestMethod.GET) public
 * String regsiter(){ return "register.html"; }
 * 
 * @RequestMapping (value="/login", method = RequestMethod.GET) public String
 * showLogin(){ return "login.html"; }
 * 
 * @RequestMapping (value="/unsubscribe", method = RequestMethod.GET) public
 * String deleteUser(){
 * 
 * return "unsubscribe.html"; }
 * 
 * @RequestMapping (value="/account", method = RequestMethod.GET) public String
 * account(){ return "account.html"; }
 * 
 * @RequestMapping (value="/addproduct", method = RequestMethod.GET) public
 * String addProduct(){ return "addproduct.html"; }
 * 
 * @RequestMapping (value="/myproducts", method = RequestMethod.GET) public
 * String myProducts(){ return "myproducts.html"; }
 * 
 * 
 * }
 */