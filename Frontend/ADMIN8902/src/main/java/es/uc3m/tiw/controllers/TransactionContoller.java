package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Transaction;

@Controller
public class TransactionContoller {
	
	@Autowired
	RestTemplate restTemplate;
	String FINA8902_URL = "http://localhost:18904/";
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String showTransactions(Model model) {
		Transaction[] transactions = restTemplate.getForObject(this.FINA8902_URL+"transactions", Transaction[].class);
		model.addAttribute("transactions", transactions);
		return "transactions.html";
	}
}
