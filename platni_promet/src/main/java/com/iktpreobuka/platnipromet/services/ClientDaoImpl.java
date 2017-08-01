package com.iktpreobuka.platnipromet.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.platnipromet.entities.ClientEntity;

@Service
public class ClientDaoImpl implements ClientDao{
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<ClientEntity> findClientByCity(String city){
		
		String sql="select c from ClientEntity c left join fetch c.address a "+
		           "where a.city = :city ";
		
		Query query=em.createQuery(sql);
		query.setParameter("city", city);
		
		List<ClientEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;
		
	}
	@Override
	public List<ClientEntity> test(){
		String sql="select c from ClientEntity c ";
		
		Query query=em.createQuery(sql);
		
		
		List<ClientEntity> result = new ArrayList<>();
		result = query.getResultList();
		return result;
		
		
	}
	

}
