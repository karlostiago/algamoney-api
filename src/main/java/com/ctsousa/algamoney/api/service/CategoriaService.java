package com.ctsousa.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ctsousa.algamoney.api.model.Categoria;
import com.ctsousa.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscaPorCodigo(Long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo);
		
		if(categoria == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return categoria;
	}
}
