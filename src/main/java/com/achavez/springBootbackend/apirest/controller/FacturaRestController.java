package com.achavez.springBootbackend.apirest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.achavez.springBootbackend.apirest.model.entity.Factura;
import com.achavez.springBootbackend.apirest.model.entity.Producto;
import com.achavez.springBootbackend.apirest.model.services.ClienteService;

@CrossOrigin(origins = { "http://localhost:4200", "*"})
@RequestMapping("/api")
@RestController
public class FacturaRestController {
	private final Logger log = LoggerFactory.getLogger(FacturaRestController.class);
	@Autowired
	private ClienteService clienteService; 
	
	@Secured({"ROLE_ADMIN",  "ROLE_USER"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Factura mostrar(@PathVariable Long id) { 
		return clienteService.findFacturaById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) { 
		clienteService.deleteFacturaById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/facturas/filtrar-productos/{term}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Producto> filtrarProductos(@PathVariable String term) { 
		return clienteService.findProductoByNombre(term);
	}
	
	//@Secured({"ROLE_ADMIN"})
	@PostMapping("/facturas")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) { 
		log.info(factura.toString());
		return clienteService.saveFactura(factura); 
	}
}
