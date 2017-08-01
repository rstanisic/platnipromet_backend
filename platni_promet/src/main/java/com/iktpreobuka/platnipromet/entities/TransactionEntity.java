package com.iktpreobuka.platnipromet.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TransactionEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String dateOfTransaction;
	
	@Column
	private String payerInfo;
	
	@Column
	private String purposeOfPayment;
	
	@Column
	private String recipientInfo;
	
	@Column
	private Double amount;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "toAccount")
	public AccountNumberEntity toAccount;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fromAccount")
	public AccountNumberEntity fromAccount;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getPayerInfo() {
		return payerInfo;
	}

	public void setPayerInfo(String payerInfo) {
		this.payerInfo = payerInfo;
	}

	public String getPurposeOfPayment() {
		return purposeOfPayment;
	}

	public void setPurposeOfPayment(String purposeOfPayment) {
		this.purposeOfPayment = purposeOfPayment;
	}

	public String getRecipientInfo() {
		return recipientInfo;
	}

	public void setRecipientInfo(String recipientInfo) {
		this.recipientInfo = recipientInfo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public AccountNumberEntity getToAccount() {
		return toAccount;
	}

	public void setToAccount(AccountNumberEntity toAccount) {
		this.toAccount = toAccount;
	}

	public AccountNumberEntity getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(AccountNumberEntity fromAccount) {
		this.fromAccount = fromAccount;
	}

	public TransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}

