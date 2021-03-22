package com.ctsousa.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctsousa.algamoney.api.model.Lancamento;
import com.ctsousa.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
