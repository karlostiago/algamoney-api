package com.ctsousa.algamoney.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ctsousa.algamoney.api.config.property.AlgamoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	private static final String OPTIONS = "OPTIONS";
	private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
	private static final String METHODS = "POST, GET, DELETE, PUT, OPTIONS";
	private static final String HEADERS = "Authorization, Content-Type, Accept";
	private static final String MAX_AGE = "3600";
	private static final String CREDENTIALS = "true";
	private static final String ORIGIN = "Origin";
	
	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	private String[] origensPermitidas;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		origensPermitidas = algamoneyApiProperty.getOrigemPermitida().split(",");
				
		response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, getOrigemPermitida(request));
		response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, CREDENTIALS);
		
		if(OPTIONS.equals(request.getMethod()) && temOriginPermitida(request)) {
			response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, METHODS);
			response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, HEADERS);
			response.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			chain.doFilter(request, response);
		}
	}
	
	private String getOrigemPermitida(HttpServletRequest request) {
		for(String origemPermitida : this.origensPermitidas) {
			if(request.getHeader(ORIGIN).equals(origemPermitida.trim())) {
				return origemPermitida;
			}
		}
		
		return null;
	}
	
	private boolean temOriginPermitida(HttpServletRequest request) {
		boolean temOrigemPermitida = false;
		
		for(String origemPermitida : this.origensPermitidas) {
			if(request.getHeader(ORIGIN).equals(origemPermitida.trim())) {
				temOrigemPermitida = true;
				break;
			}
		}
		
		return temOrigemPermitida;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
