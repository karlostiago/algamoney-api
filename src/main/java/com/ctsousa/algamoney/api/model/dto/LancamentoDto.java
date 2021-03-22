package com.ctsousa.algamoney.api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.ctsousa.algamoney.api.model.Categoria;
import com.ctsousa.algamoney.api.model.Lancamento;
import com.ctsousa.algamoney.api.model.Pessoa;
import com.ctsousa.algamoney.api.model.TipoLancamento;

public class LancamentoDto {
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String descricao;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPagamento;
	
	@NotNull
	private BigDecimal valor;
	
	private String observacao;
	
	@NotNull
	private TipoLancamento tipo;
	
	@NotNull
	private Long categoria;
	
	@NotNull
	private Long pessoa;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public Long getPessoa() {
		return pessoa;
	}

	public void setPessoa(Long pessoa) {
		this.pessoa = pessoa;
	}
	
	public Lancamento toLancamento() {
		Lancamento lancamento = new Lancamento();
		lancamento.setCategoria(new Categoria(this.categoria));
		lancamento.setPessoa(new Pessoa(this.pessoa));
		lancamento.setDataPagamento(this.dataPagamento);
		lancamento.setDataVencimento(this.dataVencimento);
		lancamento.setDescricao(this.descricao);
		lancamento.setObservacao(this.observacao);
		lancamento.setTipo(this.tipo);
		lancamento.setValor(this.valor);
		return lancamento;
	}
}
