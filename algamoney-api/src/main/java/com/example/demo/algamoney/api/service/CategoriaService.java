package com.example.demo.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.algamoney.api.model.Categoria;
import com.example.demo.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Categoria atualizar(Long codigo,Categoria categoria) {
		Categoria categoriaSalva = categoriaRepo.findOne(codigo);
		if(categoriaSalva ==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return categoriaRepo.save(categoriaSalva); 
	}
}
