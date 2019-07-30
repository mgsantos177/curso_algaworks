package com.example.demo.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
