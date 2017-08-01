package com.iktpreobuka.platnipromet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.platnipromet.entities.AccountEntity;
import com.iktpreobuka.platnipromet.entities.AccountNumberEntity;
import com.iktpreobuka.platnipromet.entities.ClientEntity;

public interface AccountRepository extends
 CrudRepository<AccountEntity,Integer>{

	List<AccountEntity> findByAccountOwner(ClientEntity AccountOwner);
	
	AccountEntity findByAccountNumber(AccountNumberEntity accountNumber);
}
