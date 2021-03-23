package com.ctsousa.algamoney.api.security;

public abstract class Authority {
	
	public static final String PESQUISAR_CATEGORIA = "hasAuthority('ROLE_PESQUISAR_CATEGORIA')";
	public static final String CADASTRAR_CATEGORIA = "hasAuthority('ROLE_CADASTRAR_CATEGORIA')";
	
	public static final String CADASTRAR_PESSOA = "hasAuthority('ROLE_CADASTRAR_PESSOA')";
	public static final String PESQUISAR_PESSOA = "hasAuthority('ROLE_PESQUISAR_PESSOA')";
	public static final String REMOVER_PESSOA = "hasAuthority('ROLE_REMOVER_PESSOA')";
	
	public static final String CADASTRAR_LANCAMENTO = "hasAuthority('ROLE_CADASTRAR_LANCAMENTO')";
	public static final String PESQUISAR_LANCAMENTO = "hasAuthority('ROLE_PESQUISAR_LANCAMENTO')";
	public static final String REMOVER_LANCAMENTO = "hasAuthority('ROLE_REMOVER_LANCAMENTO')";
}
