package com.iktpreobuka.platnipromet.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.platnipromet.controllers.util.RESTError;
import com.iktpreobuka.platnipromet.entities.AccountEntity;
import com.iktpreobuka.platnipromet.entities.AccountNumberEntity;
import com.iktpreobuka.platnipromet.entities.TransactionEntity;
import com.iktpreobuka.platnipromet.repository.AccountNumberRepository;
import com.iktpreobuka.platnipromet.repository.AccountRepository;
import com.iktpreobuka.platnipromet.repository.TransactionRepository;
import com.iktpreobuka.platnipromet.services.TransactionDao;



@RestController
@RequestMapping(path="platnipromet/transactions")


public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountNumberRepository accountNumberRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAllTransactions(){
		return new ResponseEntity<List<TransactionEntity>>((List<TransactionEntity>)transactionRepository.findAll(),HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<?> FindTransById(@PathVariable Integer id){
		TransactionEntity trans=transactionRepository.findOne(id);
		return new ResponseEntity<TransactionEntity>(trans,HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable Integer id){
		TransactionEntity transaction=transactionRepository.findOne(id);
		transactionRepository.delete(transaction);
		return new ResponseEntity<TransactionEntity>(transaction,HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addNewTransaction(
			@RequestBody TransactionEntity transaction) {
		try 
		{    
			AccountEntity accountFrom=accountRepository.findByAccountNumber(transaction.getFromAccount());
		if (accountFrom.getAccountBalance()>(transaction.getAmount()*1.01)) {
			accountFrom.setAccountBalance(accountFrom.getAccountBalance()-transaction.getAmount());
			accountRepository.save(accountFrom);
		}
		else return new ResponseEntity<RESTError>(new RESTError(1,"\t\n" + 
         	     "There are not enough funds on the account"),
                HttpStatus.CONFLICT);

	     AccountEntity accountTo=accountRepository.findByAccountNumber(transaction.getToAccount());
			accountTo.setAccountBalance(accountTo.getAccountBalance()+transaction.getAmount());
			accountRepository.save(accountTo);
	
		
		return new ResponseEntity<TransactionEntity>((TransactionEntity)transactionRepository.save(transaction),HttpStatus.OK);
		
		 } catch (Exception e) { // u slucaju izuzetka vratiti 500
	          return new ResponseEntity<RESTError>(new RESTError(2,"Exeption occurred : "+e.getMessage()),
                   	HttpStatus.INTERNAL_SERVER_ERROR);
                              }
	   }
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/toaccount/{id}")
		public ResponseEntity<?> findByToAccount(@PathVariable Integer id){
		try {
			AccountNumberEntity accountNumber=accountNumberRepository.findOne(id);
			List<TransactionEntity> payInTrans=transactionRepository.findByToAccount(accountNumber);
			return new ResponseEntity<List<TransactionEntity>>(payInTrans,HttpStatus.OK);
			
		} catch (Exception e) { // u slucaju izuzetka vratiti 500
	          return new ResponseEntity<RESTError>(new RESTError(2,"Exeption occurred : "+e.getMessage()),
	                   	HttpStatus.INTERNAL_SERVER_ERROR);
	                              }
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/fromaccount/{id}")
		public ResponseEntity<?> findByFromAccount(@PathVariable Integer id){
		try {
			AccountNumberEntity accountNumber=accountNumberRepository.findOne(id);
			List<TransactionEntity> payOutTrans=transactionRepository.findByFromAccount(accountNumber);
			return new ResponseEntity<List<TransactionEntity>>(payOutTrans,HttpStatus.OK);
			
		} catch (Exception e) { // u slucaju izuzetka vratiti 500
	          return new ResponseEntity<RESTError>(new RESTError(2,"Exeption occurred : "+e.getMessage()),
	                   	HttpStatus.INTERNAL_SERVER_ERROR);
	                              }
	}
	
	@Autowired
	private TransactionDao transDao;
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="payinclienttrans/{id}")
	public ResponseEntity<?> getClientPayInTrans(@PathVariable Integer id){
		return new ResponseEntity<List<TransactionEntity>>(transDao.findPayInTransByClient(id),HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="payoutclienttrans/{id}")
	public ResponseEntity<?> getClientPayOutTrans(@PathVariable Integer id){
		return new ResponseEntity<List<TransactionEntity>>(transDao.findPayOutTransByClient(id),HttpStatus.OK);
		
	}
	
	/*@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addNewTransactionDto(
			@RequestBody TransactionEntityDto transactionDto) {
		   
			AccountEntity accountFrom=accountRepository.findByAccountNumber(transactionDto.getFromAccountNo());
		
			accountFrom.setAccountBalance(accountFrom.getAccountBalance()-transactionDto.getAmount());
			accountRepository.save(accountFrom);

	     AccountEntity accountTo=accountRepository.findByAccountNumber(transactionDto.getToAccountNo());
			accountTo.setAccountBalance(accountTo.getAccountBalance()+transactionDto.getAmount());
			accountRepository.save(accountTo);
		
			TransactionEntity transaction=new TransactionEntity();
			transaction.setDateOfTransaction(transactionDto.getDateOfTransaction());
			transaction.setPayerInfo(transactionDto.getPayerInfo());
			transaction.setPurposeOfPayment(transactionDto.getPurposeOfPayment());
			transaction.setRecipientInfo(transactionDto.getRecipientInfo());
			transaction.fromAccount.setId(accountFrom.getId());
			transaction.fromAccount.setNo(transactionDto.getFromAccountNo());
			transaction.toAccount.setId(accountTo.getId());
			transaction.toAccount.setNo(transactionDto.getToAccountNo());
		return new ResponseEntity<TransactionEntity>((TransactionEntity)transactionRepository.save(transaction),HttpStatus.OK);
		
	}*/
}



