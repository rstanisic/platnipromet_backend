package com.iktpreobuka.platnipromet.repository;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.platnipromet.entities.BankEntity;

public interface BankRepository extends 
CrudRepository<BankEntity,Integer>{

	BankEntity findByName(String name);
}
