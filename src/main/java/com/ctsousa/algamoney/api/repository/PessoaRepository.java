package com.ctsousa.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctsousa.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
