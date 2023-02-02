package com.achavez.springBootbackend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.achavez.springBootbackend.apirest.model.entity.Usuario;
import com.achavez.springBootbackend.apirest.model.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService; 

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario =  new Usuario(); 
		usuario = usuarioService.findByUsername(authentication.getName()); 
		Map<String, Object> info = new HashMap<>(); 
		info.put("info adicional", "Hola que tal: ".concat(authentication.getName())); 
		info.put("nombre", usuario.getNombre());
		info.put("Primer_apellido", usuario.getPrimerApellido());
		info.put("segundo_apellido", usuario.getSegundoApellido());
		info.put("email", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
