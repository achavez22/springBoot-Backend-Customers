package com.achavez.springBootbackend.apirest.model.services;

import com.achavez.springBootbackend.apirest.model.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username); 
}
