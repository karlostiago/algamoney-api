package com.ctsousa.algamoney.api.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.ctsousa.algamoney.api.security.UsuarioSistema;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	private static final String NOME = "nome";
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistema sistema = (UsuarioSistema)authentication.getPrincipal();
		
		Map<String, Object> mapInfo = new HashMap<>();
		mapInfo.put(NOME, sistema.getUsuario().getNome());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(mapInfo);
		return accessToken;
	}

}
