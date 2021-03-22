package com.ctsousa.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ctsousa.algamoney.api.model.Pessoa;
import com.ctsousa.algamoney.api.model.dto.PessoaDto;
import com.ctsousa.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, PessoaDto pessoaDto) {
		Pessoa pessoa = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(pessoaDto, pessoa, "codigo");
		return pessoaRepository.save(pessoa);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoa = buscarPorCodigo(codigo);
		pessoa.setAtivo(ativo);
		pessoaRepository.save(pessoa);
	}
	
	public Pessoa buscarPorCodigo(Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		
		if(pessoa == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoa;
	}
}
