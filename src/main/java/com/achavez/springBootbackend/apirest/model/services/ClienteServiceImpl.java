package com.achavez.springBootbackend.apirest.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achavez.springBootbackend.apirest.model.dao.IClienteDao;
import com.achavez.springBootbackend.apirest.model.dao.IFacturaDao;
import com.achavez.springBootbackend.apirest.model.dao.IProductoDao;
import com.achavez.springBootbackend.apirest.model.entity.Cliente;
import com.achavez.springBootbackend.apirest.model.entity.Factura;
import com.achavez.springBootbackend.apirest.model.entity.Producto;
import com.achavez.springBootbackend.apirest.model.entity.Region;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private IClienteDao clienteDao; 
	
	@Autowired
	private IFacturaDao facturaDao; 
	
	@Autowired
	private IProductoDao productoDao; 
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteDao.findAll(pageable);
	}

	@Override
	public Cliente findbyId(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Transactional(readOnly = true)
	@Override
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Transactional
	@Override
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductoByNombre(String term) {
		
		return productoDao.findByNombreContainingIgnoreCase(term);
	}


	
	
}
