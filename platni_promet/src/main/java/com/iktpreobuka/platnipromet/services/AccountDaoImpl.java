package com.iktpreobuka.platnipromet.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.platnipromet.entities.AccountEntity;

@Service
public class AccountDaoImpl implements AccountDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<AccountEntity> payIn(Double amount,Integer id) {
		
		String sql="update accountEntity set "
				+"accountBalance=accountBalance+amount "
				+"where id = :id";
		
		Query query=em.createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("amount",amount);
		
		
		List<AccountEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
	
	
}
