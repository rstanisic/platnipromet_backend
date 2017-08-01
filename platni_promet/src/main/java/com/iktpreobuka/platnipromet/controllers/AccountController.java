package com.iktpreobuka.platnipromet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.platnipromet.controllers.util.RESTError;
import com.iktpreobuka.platnipromet.entities.AccountEntity;
import com.iktpreobuka.platnipromet.entities.BankEntity;
import com.iktpreobuka.platnipromet.entities.ClientEntity;
import com.iktpreobuka.platnipromet.repository.AccountRepository;
import com.iktpreobuka.platnipromet.repository.BankRepository;
import com.iktpreobuka.platnipromet.repository.ClientRepository;




@RestController
@RequestMapping(path="/platnipromet/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public AccountEntity addNewAccount(@RequestBody AccountEntity account) {
		accountRepository.save(account);
		
		return account;
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<AccountEntity> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable Integer id){
		try {
			Iterable<AccountEntity> accounts=getAllAccounts();
			for(AccountEntity accountEntity : accounts) {
				if(accountEntity.getId().equals(id)) {
					// ako je racun pronadjen vratiti 200
					return new ResponseEntity<AccountEntity>(accountEntity,HttpStatus.OK);
				}
			}
			// ako racun nije pronadjen vratiti 404
			return new ResponseEntity<RESTError>(new RESTError(1,"Account  not found !"),
					HttpStatus.NOT_FOUND);
		}
		catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2,"Exeption occurred : "+e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT,value="/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable Integer id,
			@RequestBody AccountEntity account){
		try {
			Iterable<AccountEntity> accounts=getAllAccounts();
		     for(AccountEntity accountEntity : accounts) {
			   if(accountEntity.getId().equals(id)) {
		          AccountEntity accountDB=accountRepository.findOne(id);
		          accountDB.setAccountNumber(account.getAccountNumber());
	           	  accountDB.setAccountBalance(account.getAccountBalance());
		          accountRepository.save(accountDB);
		
		          return new ResponseEntity<AccountEntity>(accountDB,HttpStatus.OK);
			      }
		        }
		        return new ResponseEntity<RESTError>(new RESTError(1,"Account  not found !"),
			  	                                     HttpStatus.NOT_FOUND);
		    }
		    catch (Exception e) { // u slucaju izuzetka vratiti 500
		    return new ResponseEntity<RESTError>(new RESTError(2,"Exeption occurred : "+e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	   }

	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable Integer id){
		try {
			Iterable<AccountEntity> accounts=getAllAccounts();
		      for(AccountEntity accountEntity : accounts) {
			    if(accountEntity.getId().equals(id)) {
		          accountRepository.delete(id);
		           return new ResponseEntity<AccountEntity>(accountEntity,HttpStatus.OK);
		           }
		        }
                 return new ResponseEntity<RESTError>(new RESTError(1,"Account  not found !"),
		                                           HttpStatus.NOT_FOUND);
              }
              catch (Exception e) { // u slucaju izuzetka vratiti 500
               return new ResponseEntity<RESTError>(new RESTError(2,"Exeption occurred : "+e.getMessage()),
	                                             	HttpStatus.INTERNAL_SERVER_ERROR);
             }
	    }
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT,value="/client/{id}")
	public ClientEntity addOwnerToAccount(@PathVariable Integer id,
			@RequestBody AccountEntity account) {
		ClientEntity client=clientRepository.findOne(id);
		 account.setAccountOwner(client);
		 accountRepository.save(account);
	      return client;
	}
		
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT,value="/bank")
	public AccountEntity addBankToAccount(@PathVariable Integer id,
			@RequestBody String name) {
		 AccountEntity account=accountRepository.findOne(id);
		 BankEntity bank=bankRepository.findByName(name);
		 account.setAccountInBank(bank);
		 accountRepository.save(account);
	      return account;
	}
 }
