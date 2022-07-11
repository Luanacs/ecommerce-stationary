package com.blogpessoal1.blogpessoal1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;

import com.blogpessoal1.blogpessoal1.model.Tema;
import com.blogpessoal1.blogpessoal1.repositorio.TemaRepositorio;

@RestController 
@CrossOrigin(origins = "*",allowedHeaders="*")
@RequestMapping("/temas")
public class TemaController {

	@Autowired
	private TemaRepositorio temasRepositorio;
	
	@GetMapping //método para acessar	
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temasRepositorio.findAll());
	}
	@GetMapping("/{id}") //método para acessar
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return temasRepositorio.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String descricao){
		return ResponseEntity.ok(temasRepositorio.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	@PostMapping
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema temas){
		return ResponseEntity.status(HttpStatus.CREATED).body(temasRepositorio.save(temas));
	}
	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema temas){
		return temasRepositorio.findById(temas.getId())
				.map(resposta-> ResponseEntity.status(HttpStatus.OK)
						.body(temasRepositorio.save(temas)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
		
		return temasRepositorio.findById(id)
				.map(resposta -> {
					temasRepositorio.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}