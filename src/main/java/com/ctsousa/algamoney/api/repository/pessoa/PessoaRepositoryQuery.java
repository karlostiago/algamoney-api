package com.ctsousa.algamoney.api.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ctsousa.algamoney.api.model.Pessoa;
import com.ctsousa.algamoney.api.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	public Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable);
}
