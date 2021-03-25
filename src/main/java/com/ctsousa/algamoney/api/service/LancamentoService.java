package com.ctsousa.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctsousa.algamoney.api.model.Lancamento;
import com.ctsousa.algamoney.api.model.Pessoa;
import com.ctsousa.algamoney.api.model.dto.LancamentoDto;
import com.ctsousa.algamoney.api.repository.LancamentoRepository;
import com.ctsousa.algamoney.api.repository.PessoaRepository;
import com.ctsousa.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, LancamentoDto lancamentoDto) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		BeanUtils.copyProperties(lancamentoDto, lancamento, "codigo");
		if(lancamento != null && lancamento.getPessoa().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}
}
