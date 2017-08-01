package com.iktpreobuka.platnipromet.services;

import java.util.List;

import com.iktpreobuka.platnipromet.entities.ClientEntity;

public interface ClientDao {

	public List<ClientEntity> findClientByCity(String city);

	public List<ClientEntity> test();
}
