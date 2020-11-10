package com.wooribank.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wooribank.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

	@Query(value = "select transaction.transaction_code , transaction.amount, \r\n" + 
			"		transaction.currency, transaction.receiver_acct , \r\n" + 
			"        transaction.sender_acct\r\n" + 
			"        from transaction" , nativeQuery = true) 
	public List<Object> getAllTransaction(); 
	
	@Query(value="select message.message_name , message.message_type , transaction.transaction_code ,\r\n" + 
			"		transaction.amount , transaction.currency, transaction.receiver_acct,\r\n" + 
			"        transaction.sender_acct from message \r\n" + 
			"inner join\r\n" + 
			"transaction\r\n" + 
			"on message.id = transaction.message_id" , nativeQuery = true)
	public List<Object> transferMoney();
}
