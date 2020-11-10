package com.wooribank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@Column(name = "transaction_code")
	private String transactionCode;
	
	@Column(name = "sender_acct")
	private String senderAcct;
	
	@Column(name = "receiver_acct")
	private String receiverAcct;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "currency")
	private String currency;
	
	@ManyToOne
	@JoinColumn(name = "message_id" , nullable = false)
	private Message message;

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getSenderAcct() {
		return senderAcct;
	}

	public void setSenderAcct(String senderAcct) {
		this.senderAcct = senderAcct;
	}

	public String getReceiverAcct() {
		return receiverAcct;
	}

	public void setReceiverAcct(String receiverAcct) {
		this.receiverAcct = receiverAcct;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Message getMessage() {
		return message;
	}

}
