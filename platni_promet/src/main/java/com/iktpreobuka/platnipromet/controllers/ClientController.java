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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.iktpreobuka.platnipromet.entities.AccountEntity;
import com.iktpreobuka.platnipromet.entities.AddressEntity;
import com.iktpreobuka.platnipromet.entities.ClientEntity;
import com.iktpreobuka.platnipromet.repository.AccountRepository;
import com.iktpreobuka.platnipromet.repository.AddressRepository;
import com.iktpreobuka.platnipromet.repository.ClientRepository;


@RestController
@RequestMapping(path="/platnipromet/clients")
public class ClientController {
	


	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	

	 @CrossOrigin
	   @RequestMapping(method=RequestMethod.POST)
	   public ResponseEntity<?> addClient(@RequestBody ClientEntity client){
		   return new ResponseEntity<ClientEntity>(clientRepository.save(client),HttpStatus.OK);
	   }

	

	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAllClients(){
		return new ResponseEntity<List<ClientEntity>>((List<ClientEntity>)clientRepository.findAll(),HttpStatus.OK);
	}
	
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/address")
	public ClientEntity addAddressToAUser(@PathVariable Integer id,
	@RequestParam Integer address) {
	ClientEntity client = clientRepository.findOne(id);
	AddressEntity adr = addressRepository.findOne(address);
	client.setAddress(adr);
	clientRepository.save(client); // automatski ce biti sacuvana i adresa
	return client;
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<?> getClientById(@PathVariable Integer id){
			ClientEntity clientDB=clientRepository.findOne(id);
				
	return new ResponseEntity<ClientEntity>(clientDB,HttpStatus.OK);
	
		}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ClientEntity deleteUser(@PathVariable Integer id) {;
		ClientEntity client=clientRepository.findOne(id);
		
		clientRepository.delete(id);
		
	
return client;
		}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/by-email")
	public ClientEntity findByEmail(@RequestParam String email) {
		return clientRepository.findByEmail(email);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/by-name")
	public List<ClientEntity> findByName(@RequestParam String name) {
		return clientRepository.findByName(name);
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/by-jmbg")
	public ClientEntity findByJmbg(@RequestParam Long jmbg) {
		return clientRepository.findByJmbg(jmbg);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/jmbg/{id}")
	public ClientEntity addJmbgToAUser(@PathVariable Integer id,
	@RequestParam Long jmbg) {
	ClientEntity client = clientRepository.findOne(id);
	
	client.setJmbg(jmbg);
	clientRepository.save(client); 
	return client;
	}
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/password/{id}")
	public ClientEntity addPasswordToAUser(@PathVariable Integer id,
	@RequestParam String password) {
	ClientEntity client = clientRepository.findOne(id);
	
	client.setPassword(password);
	clientRepository.save(client); 
	return client;
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/clientaccounts/{id}")
	public List<AccountEntity> findClientAccounts(@PathVariable Integer id) {
	  ClientEntity client = clientRepository.findOne(id);
	  List<AccountEntity> accounts=accountRepository.findByAccountOwner(client);
	  return accounts;
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/clientaddress/{id}")
			public AddressEntity findClientAddress(@PathVariable Integer id) {
		
		ClientEntity client=clientRepository.findOne(id);
		AddressEntity address=addressRepository.findByClients(client);
		return address;
	}
@CrossOrigin
@RequestMapping(method=RequestMethod.PUT,value="/{id}")
public ResponseEntity<?> updateClient(@PathVariable Integer id,
		@RequestBody ClientEntity client){
	ClientEntity clientDB=clientRepository.findOne(id);
	clientDB.setName(client.getName());
	clientDB.setDateOfBirth(client.getDateOfBirth());
	clientDB.setEmail(client.getEmail());
	clientDB.setPassword(client.getPassword());
	clientDB.setBrojLk(client.getBrojLk());
    clientDB.setJmbg(client.getJmbg());
    clientDB.setBrojTel(client.getBrojTel());
    clientRepository.save(clientDB);
    return new ResponseEntity<ClientEntity>(clientDB,HttpStatus.OK);
}   
@CrossOrigin
@RequestMapping(method=RequestMethod.PUT,value="/client/{id}")
public ClientEntity addAddressToClient(@PathVariable Integer id,
		@RequestBody AddressEntity address) {
	
	ClientEntity client=clientRepository.findOne(id);
	 addressRepository.save(address);
	 client.setAddress(address);
	 clientRepository.save(client);
     return client;
}

}

