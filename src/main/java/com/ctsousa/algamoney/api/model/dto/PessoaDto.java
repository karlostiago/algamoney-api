package com.ctsousa.algamoney.api.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ctsousa.algamoney.api.model.Endereco;
import com.ctsousa.algamoney.api.model.Pessoa;

public class PessoaDto {
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	
	private Endereco endereco;

	@NotNull
	private Boolean ativo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Pessoa toPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(this.nome);
		pessoa.setAtivo(this.ativo);
		pessoa.setEndereco(this.endereco);
		return pessoa;
	}
}
