package com.iktpreobuka.platnipromet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.platnipromet.entities.AccountNumberEntity;
import com.iktpreobuka.platnipromet.entities.TransactionEntity;


@Repository
public interface TransactionRepository extends
CrudRepository<TransactionEntity,Integer>{
	List<TransactionEntity> findByToAccount(AccountNumberEntity toAccount);
	List<TransactionEntity> findByFromAccount(AccountNumberEntity fromAccount);
	
}