package com.iktpreobuka.platnipromet.repository;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.platnipromet.entities.AddressEntity;
import com.iktpreobuka.platnipromet.entities.ClientEntity;

public interface AddressRepository extends
CrudRepository<AddressEntity,Integer>{

	AddressEntity findByClients(ClientEntity clients);
}
