package com.achavez.springBootbackend.apirest.model.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements UploadFileService{
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	private final static String DIR_UPLOAD = "uploads"; 
	
	@Override
	public Resource load(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		
		log.info(rutaArchivo.toString());
		
		Resource resource = new UrlResource(rutaArchivo.toUri());
		
		if(!resource.exists() && !resource.isReadable()) { 
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();
			
			resource = new UrlResource(rutaArchivo.toUri());
			
			log.error("Error no se pudo cargar la imagen" + nombreFoto);
			
		}
		return resource;
	}

	@Override
	public String copy(MultipartFile archivo) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString() +"_"+   archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo);
		
		log.info(rutaArchivo.toString());
		
		
		Files.copy(archivo.getInputStream(), rutaArchivo );
		
		return nombreArchivo;
	}

	@Override
	public boolean delete(String nombreFoto) {
		
		if(nombreFoto != null && nombreFoto.length() > 0) { 
			Path rutaFotoAnterior =  getPath(nombreFoto);
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) { 
				archivoFotoAnterior.delete(); 
				return true; 
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		
		return Paths.get(DIR_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
