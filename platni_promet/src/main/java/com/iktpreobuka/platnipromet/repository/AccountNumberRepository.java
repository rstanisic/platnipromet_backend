package com.iktpreobuka.platnipromet.repository;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.platnipromet.entities.AccountEntity;
import com.iktpreobuka.platnipromet.entities.AccountNumberEntity;



public interface AccountNumberRepository extends
CrudRepository<AccountNumberEntity,Integer>{

	AccountNumberEntity findByNo(String no);
	AccountNumberEntity findByAccount(AccountEntity account);
}
