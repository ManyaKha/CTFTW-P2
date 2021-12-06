package es.uc3m.tiw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.domains.Transaction;
import es.uc3m.tiw.repositories.TransactionRepository;

@RestController
@EnableAutoConfiguration
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = "application/json")
	public List<Transaction> getTransactions(){
		System.out.println("HEIS");
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions;
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public void createTransaction(@RequestBody Transaction transaction) {
		transactionRepository.save(transaction);
	}

}
