package com.iktpreobuka.platnipromet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.iktpreobuka.platnipromet.entities.AddressEntity;
import com.iktpreobuka.platnipromet.entities.BankEntity;
import com.iktpreobuka.platnipromet.entities.ClientEntity;
import com.iktpreobuka.platnipromet.repository.AddressRepository;
import com.iktpreobuka.platnipromet.repository.BankRepository;


@Controller
@RequestMapping(path="/platnipromet/banks")
public class BankController {

	@Autowired
    private BankRepository bankRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAllBanks(){
		return new ResponseEntity<List<BankEntity>>((List<BankEntity>)bankRepository.findAll(),HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addNewBank(@RequestBody BankEntity bank){
		  bankRepository.save(bank);
		   return new ResponseEntity<BankEntity>(bank,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<?> getBankById(@PathVariable Integer id){
			BankEntity bankDB=bankRepository.findOne(id);
				
	return new ResponseEntity<BankEntity>(bankDB,HttpStatus.OK);
	
		}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<?> deleteBank(@PathVariable Integer id){
		BankEntity bankEntity=bankRepository.findOne(id);
			 bankRepository.delete(id);
		     return new ResponseEntity<BankEntity>(bankEntity,HttpStatus.OK);
    }
		
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/address/{id}")
	public BankEntity addAddressToBank(@PathVariable Integer id,
	@RequestBody  AddressEntity address) {
		addressRepository.save(address);
		BankEntity bank = bankRepository.findOne(id);
	    bank.setAddress(address);
	    bankRepository.save(bank);
	     return bank;
	 }
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT,value="/{id}")
	public ResponseEntity<?> updateBank(@PathVariable Integer id,
			@RequestBody BankEntity bank){
		BankEntity bankDB=bankRepository.findOne(id);
		bankDB.setName(bank.getName());
		bankDB.setAddress(bank.getAddress());
		bankRepository.save(bank);
	    return new ResponseEntity<BankEntity>(bankDB,HttpStatus.OK);
	}
}

