package com.iktpreobuka.platnipromet.services;

import java.util.List;

import com.iktpreobuka.platnipromet.entities.TransactionEntity;

public interface TransactionDao {

	public List<TransactionEntity> findPayInTransByClient(Integer id);
	
	public List<TransactionEntity> findPayOutTransByClient(Integer id);
}

