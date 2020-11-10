package com.wooribank.api;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wooribank.entity.Transaction;
import com.wooribank.repository.TransactionRepository;

@RestController
@RequestMapping("/bank/")
public class TransactionController {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@GetMapping("/getall")
	public List<Transaction> findTransaction(){
		return (List<Transaction>) transactionRepository.findAll();
	}
	
	@GetMapping("/gettransaction")
	public List<Object> getTransactionCode(){
		return transactionRepository.getAllTransaction();
	}
	
	@GetMapping("/transfermoney")
	public List<Object> getTranfer(){
		return transactionRepository.transferMoney();
	}
	
	@PostMapping("/savetransaction")
	public Transaction saveTransaction(@RequestBody Transaction transaction) {
		
		String transactionCode = transaction.getTransactionCode();
		Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionCode);
				
		if(optionalTransaction.isPresent()) {
			return null;
		}else {
			return transactionRepository.save(transaction);
		}
	}
	
	@PutMapping("/processtransaction")
	public ResponseEntity<Transaction> editTransaction(@RequestBody Transaction transaction){
		
		String senderAcct = transaction.getSenderAcct();
		String receiverAcct = transaction.getReceiverAcct();
		String currency = transaction.getCurrency();
		
		Transaction optionalTransaction = transactionRepository.findById(transaction.getTransactionCode())
				.orElseThrow(() -> new com.wooribank.exception.ResourceNotFoundException("Transaction not exist with id : "));
		
		String checkSenderAcct = optionalTransaction.getSenderAcct();
		String checkReceiverAcct = optionalTransaction.getReceiverAcct();
		String checkCurrency = optionalTransaction.getCurrency();
		
		if(checkSenderAcct.equals(senderAcct) && checkReceiverAcct.equals(receiverAcct) 
				&& checkCurrency.equals(currency) && optionalTransaction.getMessage().equals(transaction.getMessage())) {
			optionalTransaction.setAmount(transaction.getAmount() + optionalTransaction.getAmount());
			Transaction transactionUpdate = transactionRepository.save(optionalTransaction);
			
			return ResponseEntity.ok(transactionUpdate);
		}else {
			System.out.println(senderAcct + "  " + receiverAcct + "  " + currency);
			System.out.println(checkSenderAcct + "  " + checkReceiverAcct + "  " + checkCurrency);
			System.out.println("transaction false");
			return null;
		}
		
			}
	
}
