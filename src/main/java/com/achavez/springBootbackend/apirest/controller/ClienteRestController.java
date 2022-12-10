package com.achavez.springBootbackend.apirest.controller;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.achavez.springBootbackend.apirest.model.entity.Cliente;
import com.achavez.springBootbackend.apirest.model.services.ClienteService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "localhost:4200"}) // configuracion para el puerto de angular. podemos configurar metodos y cabeceras permitidas
public class ClienteRestController { 
	
	
	@Autowired
	ClienteService clienteService; 
	
	@GetMapping("/clientes")
	public List<Cliente> index(){ 
		return clienteService.findAll(); 
	}
	
	
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findbyId(id); 
		
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) { 
		//cliente.setCreateAt(new Date());
		return clienteService.save(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente,  @PathVariable Long id) { 
		Cliente clienteActual = new Cliente(); 
		clienteActual.setNombre(cliente.getNombre()); 
		clienteActual.setPrimerApellido(cliente.getPrimerApellido());
		clienteActual.setSegundoApellido(cliente.getSegundoApellido()); 
		clienteActual.setEmail(cliente.getEmail()); 
		
		return clienteService.save(clienteActual);
		
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) { 
		clienteService.delete(id);
	}
	
	
}