package com.iktpreobuka.platnipromet.services;

import java.util.List;

import com.iktpreobuka.platnipromet.entities.AccountEntity;

public interface AccountDao {
	
	public List<AccountEntity> payIn(Double amount,Integer id);

}
