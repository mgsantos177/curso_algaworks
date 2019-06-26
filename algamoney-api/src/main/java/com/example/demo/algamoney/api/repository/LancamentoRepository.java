package com.example.demo.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.algamoney.api.model.Lancamento;
import com.example.demo.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
