package com.iktpreobuka.platnipromet.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

public class TransactionDto {


	@JsonRootName (value="no")
	public class TransactionEntityDto {
	    @JsonProperty("dateOfTransactin")
		private String dateOfTransaction;
	    
	    @JsonProperty("payerInfo")
		private String payerInfo;
	    
	    @JsonProperty("purposeOfPayment")
	    private String purposeOfPayment;
	    
	    @JsonProperty("recipientInfo")
	    private String recipientInfo;
	    
	    @JsonProperty("amount")
	    private Double amount;
	    
	    
	    @JsonProperty("no")
	    private String fromAccountNo;
	    
	   
	    @JsonProperty("no")
	    private String toAccountNo;

		public String getDateOfTransaction() {
			return dateOfTransaction;
		}


		public String getPayerInfo() {
			return payerInfo;
		}


		public String getPurposeOfPayment() {
			return purposeOfPayment;
		}

		public String getRecipientInfo() {
			return recipientInfo;
		}

		public Double getAmount() {
			return amount;
		
		}
		public String getFromAccountNo() {
			return fromAccountNo;
		}

		

		public String getToAccountNo() {
			return toAccountNo;
		}

		

		public TransactionEntityDto(String dateOfTransaction, String payerInfo, String purposeOfPayment,
				String recipientInfo, Double amount, String fromAccountNo, String toAccountNo) {
			super();
			this.dateOfTransaction = dateOfTransaction;
			this.payerInfo = payerInfo;
			this.purposeOfPayment = purposeOfPayment;
			this.recipientInfo = recipientInfo;
			this.amount = amount;
			this.fromAccountNo = fromAccountNo;
			this.toAccountNo = toAccountNo;
		}

		public TransactionEntityDto() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	}

}
