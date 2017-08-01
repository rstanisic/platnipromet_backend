package com.iktpreobuka.platnipromet.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.platnipromet.entities.TransactionEntity;



@Service
public class TransactionDaoImpl implements TransactionDao {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<TransactionEntity> findPayInTransByClient(Integer id){
		
		String sql="select t from TransactionEntity t left join fetch "+
		"t.toAccount to left join fetch to.account ac left join fetch ac.accountOwner ao "+
				"where ao.id=:id";
		Query query=em.createQuery(sql);
		query.setParameter("id", id);
		
		List<TransactionEntity> result=new ArrayList<>();
		result=query.getResultList();
		return result;
	}
	
	@Override
	public List<TransactionEntity> findPayOutTransByClient(Integer id){
		
		String sql="select t from TransactionEntity t left join fetch "+
		"t.fromAccount fo left join fetch fo.account ac left join fetch ac.accountOwner ao "+
				"where ao.id=:id";
	
		Query query=em.createQuery(sql);
		query.setParameter("id", id);
		
		List<TransactionEntity> result=new ArrayList<>();
		result=query.getResultList();
		return result;
	}
}
