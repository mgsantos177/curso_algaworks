package com.example.demo.algamoney.api.repository.lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.algamoney.api.model.Lancamento;
import com.example.demo.algamoney.api.repository.filter.LancamentoFilter;
import com.example.demo.algamoney.api.repository.projection.ResumoLancamento;
public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable );
	
	public Page<ResumoLancamento>resumo(LancamentoFilter lancamentoFilter, Pageable pageable);
	

}
