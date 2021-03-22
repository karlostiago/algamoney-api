package com.ctsousa.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctsousa.algamoney.api.event.RecursoCriadoEvent;
import com.ctsousa.algamoney.api.model.Categoria;
import com.ctsousa.algamoney.api.model.dto.CategoriaDto;
import com.ctsousa.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource extends AbstractResource<Categoria> {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List	<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody CategoriaDto categoriaDto, HttpServletResponse response) {
		Categoria categoria = new Categoria();
		categoria = categoriaRepository.save(categoriaDto.toCategoria());
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Categoria categoria = categoriaRepository.findOne(codigo);
		
		if(categoria == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(categoria);
	}
}
