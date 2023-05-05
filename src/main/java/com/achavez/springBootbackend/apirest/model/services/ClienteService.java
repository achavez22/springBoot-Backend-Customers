package com.achavez.springBootbackend.apirest.model.services;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.achavez.springBootbackend.apirest.model.entity.Cliente;
import com.achavez.springBootbackend.apirest.model.entity.Factura;
import com.achavez.springBootbackend.apirest.model.entity.Producto;
import com.achavez.springBootbackend.apirest.model.entity.Region;

public interface ClienteService {
	
	public List<Cliente> findAll(); 
	
	public Page<Cliente> findAll(Pageable pageable); 
	
	public Cliente findbyId(Long id);
	   
    public Cliente save(Cliente cliente);

    public void delete(Long id);

    public List<Region> findAllRegiones();
    
    public Factura findFacturaById(Long id);
    
    public Factura saveFactura(Factura factura);
    
    public void deleteFacturaById(Long id);
    
    public List<Producto> findProductoByNombre(String term); 
    
    
	
}
