package com.iktpreobuka.platnipromet.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.platnipromet.entities.BankEntity;

@Service
public class BankDaoImpl implements BankDao{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<BankEntity> getAllBanks(){
		String sql="select b from BankEntity b";
		
		Query query=em.createQuery(sql);
		
		List<BankEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;
	}
}
