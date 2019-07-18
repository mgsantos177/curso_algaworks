package com.example.demo.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
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

import com.example.demo.algamoney.api.event.RecursoCriadoEvent;
import com.example.demo.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.demo.algamoney.api.model.Lancamento;
import com.example.demo.algamoney.api.repository.LancamentoRepository;
import com.example.demo.algamoney.api.repository.filter.LancamentoFilter;
import com.example.demo.algamoney.api.service.LancamentoService;
import com.example.demo.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepo;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/filtrar")
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoRepo.filtrar(lancamentoFilter, pageable);
	}
	
	
	@GetMapping("/listar")
	public List <Lancamento> listar(){
		return lancamentoRepo.findAll(); 
	}
	
	@GetMapping("/listar/{codigo}")
	public ResponseEntity<Lancamento> findById(@PathVariable Long codigo){
		Lancamento lancamento = lancamentoRepo.findOne(codigo);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Lancamento> cadastrar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoSalvo = lancamentoService.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this,response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);		
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa.inativa-ou-inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigo) {
		lancamentoRepo.delete(codigo);
	}
}	
