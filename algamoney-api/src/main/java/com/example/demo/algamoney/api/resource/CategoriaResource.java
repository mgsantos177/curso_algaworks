package com.example.demo.algamoney.api.resource;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.algamoney.api.event.RecursoCriadoEvent;
import com.example.demo.algamoney.api.model.Categoria;
import com.example.demo.algamoney.api.repository.CategoriaRepository;
import com.example.demo.algamoney.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

		@Autowired
		private CategoriaRepository categoriaRepo;
		
		@Autowired
		private ApplicationEventPublisher publisher;
		
		@Autowired
		private CategoriaService categoriaService;
		
		@CrossOrigin(maxAge = 10, origins = "http://localhost:8000")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
		@GetMapping("/listar")
		public List<Categoria> listar(){
			return categoriaRepo.findAll();
		}
		
		@GetMapping("/listar/{codigo}")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
		public ResponseEntity<Categoria> findById(@PathVariable Long codigo) {
				Categoria categoria = categoriaRepo.findOne(codigo);
				return categoria != null ? ResponseEntity.ok(categoria):ResponseEntity.notFound().build();
		}
		
		@PostMapping("/cadastrar")
		@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA')and #oauth2.hasScope('write')")
		public ResponseEntity <Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
			Categoria categoriaSalva = categoriaRepo.save(categoria);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		}
		

		
		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deletar(@PathVariable Long codigo) {
			categoriaRepo.delete(codigo);
			
		}
		
		@PutMapping("/atualizar/{codigo}")
		public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
			Categoria categoriaSalva = categoriaService.atualizar(codigo, categoria);
			return ResponseEntity.ok(categoriaSalva);	
		}	
}
