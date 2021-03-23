package com.ctsousa.algamoney.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	private static final String REFRESH_TOKEN = "refreshToken";
	private static final String PATH_OAUTH_TOKEN = "/oauth/token";
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(REFRESH_TOKEN, null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath(request.getContextPath() + PATH_OAUTH_TOKEN);
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
