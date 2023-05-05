package com.achavez.springBootbackend.apirest.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.achavez.springBootbackend.apirest.model.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
