package com.ctsousa.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctsousa.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
