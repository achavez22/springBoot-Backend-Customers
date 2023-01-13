package com.achavez.springBootbackend.apirest.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.achavez.springBootbackend.apirest.model.entity.Cliente;

public interface IClienteDao  extends JpaRepository<Cliente, Long>{
	
}
