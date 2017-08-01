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

import com.iktpreobuka.platnipromet.entities.AccountEntity;
import com.iktpreobuka.platnipromet.entities.AddressEntity;
import com.iktpreobuka.platnipromet.entities.ClientEntity;
import com.iktpreobuka.platnipromet.repository.AddressRepository;

@RestController
@RequestMapping(path="/platnipromet/addresses")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public AddressEntity addNewAddress(@RequestBody AddressEntity address) {
		addressRepository.save(address);
	     return address;
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAddresses() {
		return new ResponseEntity<List<AddressEntity>>((List<AddressEntity>)
				addressRepository.findAll(), HttpStatus.OK); }
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getHeroById(@PathVariable Integer id){
		AddressEntity addressDB = addressRepository.findOne(id);
		return new ResponseEntity<AddressEntity>(addressDB, HttpStatus.OK); 
		}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteAddresses(@PathVariable Integer id) {
		AddressEntity addressDB = addressRepository.findOne(id);
		addressRepository.delete(addressDB);
		return new ResponseEntity<AddressEntity>(addressDB, HttpStatus.OK); }
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateAddress(@PathVariable Integer id, @RequestBody AddressEntity address)
	{ AddressEntity addressDB = addressRepository.findOne(id);
	addressDB.setStreet(address.getStreet());
	addressDB.setCity(address.getCity());
	addressDB.setCountry(address.getCountry());
	addressRepository.save(addressDB);
	return new ResponseEntity<AddressEntity>(addressDB, HttpStatus.OK);
	}
	
	
	
	

}
