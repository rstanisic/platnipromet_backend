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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class AccountEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private AccountNumberEntity accountNumber;
	
	@Column
	private Double accountBalance;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "accountOwner")
	private ClientEntity accountOwner;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "accountInBank")
	private BankEntity accountInBank;
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public AccountNumberEntity getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(AccountNumberEntity accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}


	public ClientEntity getAccountOwner() {
		return accountOwner;
	}


	public void setAccountOwner(ClientEntity accountOwner) {
		this.accountOwner = accountOwner;
	}


	public BankEntity getAccountInBank() {
		return accountInBank;
	}


	public void setAccountInBank(BankEntity accountInBank) {
		this.accountInBank = accountInBank;
	}




	public AccountEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}
