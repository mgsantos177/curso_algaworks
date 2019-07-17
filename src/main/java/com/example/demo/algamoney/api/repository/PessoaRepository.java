package com.example.demo.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}
