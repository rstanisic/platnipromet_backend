package com.iktpreobuka.platnipromet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.platnipromet.entities.ClientEntity;


public interface ClientRepository extends
CrudRepository<ClientEntity,Integer>{

	ClientEntity findByEmail(String email );

	List<ClientEntity> findByName(String name);
	
	ClientEntity findByJmbg(Long jmbg);
}
