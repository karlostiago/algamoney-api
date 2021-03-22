package com.ctsousa.algamoney.api.model.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ctsousa.algamoney.api.model.Categoria;

public class CategoriaDto {
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria toCategoria() {
		return new Categoria(this.nome);
	}
}
