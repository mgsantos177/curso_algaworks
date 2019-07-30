package com.example.demo.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.algamoney.api.model.Lancamento;
import com.example.demo.algamoney.api.model.Pessoa;
import com.example.demo.algamoney.api.repository.LancamentoRepository;
import com.example.demo.algamoney.api.repository.PessoaRepository;
import com.example.demo.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService{
	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Autowired
	private LancamentoRepository lancamentoRepo;

	public Lancamento save(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepo.findOne(lancamento.getPessoa().getCodigo());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepo.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if(!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		
		return lancamentoRepo.save(lancamentoSalvo);
	}

	private Lancamento buscarLancamentoExistente(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepo.findOne(codigo);
		if(lancamentoSalvo ==null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}

	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		
		if(lancamento.getPessoa().getCodigo()!= null) {
			pessoa = pessoaRepo.findOne(lancamento.getPessoa().getCodigo());
		}
		if(pessoa== null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}
	
	

}
