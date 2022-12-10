package com.achavez.springBootbackend.apirest.model.services;

import java.util.List;

import com.achavez.springBootbackend.apirest.model.entity.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll(); 
	
	
	public Cliente findbyId(Long id);
	   
    public Cliente save(Cliente cliente);

    public void delete(Long id);
	
}
