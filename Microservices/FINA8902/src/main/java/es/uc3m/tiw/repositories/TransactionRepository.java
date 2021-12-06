package es.uc3m.tiw.repositories;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.tiw.domains.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
	

}
