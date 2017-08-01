package com.iktpreobuka.platnipromet.services;

import java.util.List;

import com.iktpreobuka.platnipromet.entities.AddressEntity;

public interface AddressDao {
	
	public List<AddressEntity> findAdressesByClientName(String name);

}
