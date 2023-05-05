package com.achavez.springBootbackend.apirest.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.achavez.springBootbackend.apirest.model.entity.Producto;
import java.util.List;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term); 
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);
}
