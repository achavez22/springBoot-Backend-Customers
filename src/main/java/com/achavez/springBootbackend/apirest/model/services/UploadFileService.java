package com.achavez.springBootbackend.apirest.model.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
	
	public Resource load(String nombreFoto) throws MalformedURLException;
	public String copy(MultipartFile archivo) throws IOException;
	public boolean delete(String nombreFoto); 
	public Path getPath(String nombreFoto); 
	
}
