package com.ctsousa.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ctsousa.algamoney.api.event.RecursoCriadoEvent;
import com.ctsousa.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.ctsousa.algamoney.api.model.Lancamento;
import com.ctsousa.algamoney.api.model.dto.LancamentoDto;
import com.ctsousa.algamoney.api.repository.LancamentoRepository;
import com.ctsousa.algamoney.api.repository.filter.LancamentoFilter;
import com.ctsousa.algamoney.api.service.CategoriaService;
import com.ctsousa.algamoney.api.service.LancamentoService;
import com.ctsousa.algamoney.api.service.PessoaService;
import com.ctsousa.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource extends AbstractResource<Lancamento> {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable) {
		return lancamentoRepository.filtrar(filter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
		try {
			
			Lancamento lancamento = lancamentoRepository.findOne(codigo);
			
			if(lancamento == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(lancamento);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody LancamentoDto lancamentoDto, HttpServletResponse response) {
		Lancamento lancamento = new Lancamento();
		lancamento = lancamentoService.salvar(lancamentoDto.toLancamento());
		lancamento.setCategoria(categoriaService.buscaPorCodigo(lancamentoDto.getCategoria()));
		lancamento.setPessoa(pessoaService.buscarPorCodigo(lancamentoDto.getPessoa()));
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigo) {
		lancamentoRepository.delete(codigo);
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}