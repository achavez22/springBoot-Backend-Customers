package com.achavez.springBootbackend.apirest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.achavez.springBootbackend.apirest.model.entity.Cliente;
import com.achavez.springBootbackend.apirest.model.entity.Region;
import com.achavez.springBootbackend.apirest.model.services.ClienteService;
import com.achavez.springBootbackend.apirest.model.services.UploadFileService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "*"}) // configuracion para el puerto de angular. 
public class ClienteRestController { 
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Autowired
	ClienteService clienteService; 
	
	
	@Autowired
	UploadFileService uploadFileService; 
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){ 
		Pageable pageable =  PageRequest.of(page, 5);
		return clienteService.findAll(pageable); 
	}
	
	@GetMapping("/clientes")
	public List<Cliente> index(){ 
		return clienteService.findAll(); 
	}
	
	@Secured({"ROLE_ADMIN",  "ROLE_USER"})
	@GetMapping(path = "/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
		
        Cliente cliente =  null;
        
        Map<String, Object> response = new HashMap<>();
        
        try{
            cliente = clienteService.findbyId(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos. ");
            response.put("error", e.getMessage().concat(" : " ).concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(cliente == null) {
            response.put("mensaje", "El cliente con el id ".concat(id.toString().concat(" no existe en la base de datos ")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }
	
	@Secured({"ROLE_ADMIN"})
    @PostMapping( "/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody  Cliente cliente, BindingResult result){
        Cliente clienteNuevo;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            clienteNuevo = clienteService.save(cliente);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert  en la base de datos. ");
            response.put("error", e.getMessage().concat(" : " ).concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", clienteNuevo);
        return new ResponseEntity<Map<String, Object>>(response , HttpStatus.CREATED);
    }

	@Secured({"ROLE_ADMIN"})
    @PutMapping( "/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable Long id ){
        Map<String, Object> response = new HashMap<>();
        Cliente clienteActual = clienteService.findbyId(id);
        Cliente clienteUpdated;
        log.info(cliente.toString());
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(clienteActual == null) {
            response.put("mensaje", "No se puede editar, el cliente id ".concat(id.toString().concat(" no existe en la base de datos ")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            clienteActual.setPrimerApellido(cliente.getPrimerApellido());
            clienteActual.setSegundoApellido(cliente.getSegundoApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt()); 
            clienteActual.setRegion(cliente.getRegion());

            clienteUpdated = clienteService.save(clienteActual);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos. ");
            response.put("error", e.getMessage().concat(" : " ).concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido actualizado con exito");
        response.put("cliente", clienteUpdated);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

	@Secured({"ROLE_ADMIN"})
    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?>  delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
        	Cliente cliente =  clienteService.findbyId(id); 
        	String nombreFotoAnterior = cliente.getFoto();
        	uploadFileService.delete(nombreFotoAnterior); 
        	
            clienteService.delete(id);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en la base de datos. ");
            response.put("error", e.getMessage().concat(" : " ).concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN",  "ROLE_USER"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){ 
    	Map<String, Object> response = new HashMap<>();
    	
    	Cliente cliente =  clienteService.findbyId(id); 
    	
    	if(!archivo.isEmpty()) { 

    		String nombreArchivo = null; 
    		try {
    			nombreArchivo = uploadFileService.copy(archivo);
			} catch (IOException e) {
				response.put("mensaje", " Error al subir la imagen");
				response.put("error", e.getMessage().concat(" : " ).concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} 
    		String nombreFotoAnterior = cliente.getFoto();
    		uploadFileService.delete(nombreFotoAnterior); 
    		
    		cliente.setFoto(nombreArchivo);
    		
    		clienteService.save(cliente);
    		
    		response.put("cliente", cliente); 
    		response.put("mensaje", "Has subido correctamente la imagen; " + nombreArchivo);
    		
    	}
    	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN",  "ROLE_USER"})
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){ 
    	
		
		Resource resource = null; 
		
		try {
			resource = uploadFileService.load(nombreFoto);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders header = new HttpHeaders(); 
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		
    	return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
    
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/clientes/regiones")
    public List<Region> listarRegiones(){ 
    	return clienteService.findAllRegiones();
    }
    
	
}
