package com.achavez.springBootbackend.apirest.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.achavez.springBootbackend.apirest.model.entity.Cliente;

public interface IClienteDao  extends CrudRepository<Cliente, Long>{
	
}
