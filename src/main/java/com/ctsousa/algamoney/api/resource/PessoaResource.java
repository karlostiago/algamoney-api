package com.ctsousa.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ctsousa.algamoney.api.event.RecursoCriadoEvent;
import com.ctsousa.algamoney.api.model.Pessoa;
import com.ctsousa.algamoney.api.model.dto.PessoaDto;
import com.ctsousa.algamoney.api.repository.PessoaRepository;
import com.ctsousa.algamoney.api.repository.filter.PessoaFilter;
import com.ctsousa.algamoney.api.security.Authority;
import com.ctsousa.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource extends AbstractResource<Pessoa> {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	@PreAuthorize(Authority.PESQUISAR_PESSOA)
	public Page<Pessoa> pesquisar(PessoaFilter filter, Pageable pageable) {
		return pessoaRepository.filtrar(filter, pageable);
	}
	
	@PostMapping
	@PreAuthorize(Authority.CADASTRAR_PESSOA)
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody PessoaDto pessoaDto, HttpServletResponse response) {
		Pessoa pessoa = new Pessoa();
		pessoa = pessoaRepository.save(pessoaDto.toPessoa());
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize(Authority.PESQUISAR_PESSOA)
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PreAuthorize(Authority.REMOVER_PESSOA)
	public void delete(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody PessoaDto pessoaDto) {
		Pessoa pessoa = pessoaService.atualizar(codigo, pessoaDto);
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
