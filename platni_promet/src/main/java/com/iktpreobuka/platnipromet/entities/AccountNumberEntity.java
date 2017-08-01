package com.iktpreobuka.platnipromet.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class AccountNumberEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String no;
	

	
	@JsonIgnore
	@OneToOne( mappedBy="accountNumber",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private AccountEntity account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "toAccount", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
     private List<TransactionEntity> payInTrans=new ArrayList<>();

	
	@JsonIgnore
	@OneToMany(mappedBy = "fromAccount", fetch = FetchType.LAZY, cascade = { CascadeType.ALL})
     private List<TransactionEntity> payOutTrans=new ArrayList<>();
	

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public List<TransactionEntity> getPayInTrans() {
		return payInTrans;
	}

	public void setPayInTrans(List<TransactionEntity> payInTrans) {
		this.payInTrans = payInTrans;
	}

	public List<TransactionEntity> getPayOutTrans() {
		return payOutTrans;
	}

	public void setPayOutTrans(List<TransactionEntity> payOutTrans) {
		this.payOutTrans = payOutTrans;
	}

	public String getNo() {
		return no;
	}

	public void setNo( String no) {
		this.no = no;
	}

	public AccountNumberEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

