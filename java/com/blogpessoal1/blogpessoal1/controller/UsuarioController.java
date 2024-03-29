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

import com.blogpessoal1.blogpessoal1.model.Usuario;
import com.blogpessoal1.blogpessoal1.model.UsuarioLogin;
import com.blogpessoal1.blogpessoal1.repositorio.UsuarioRepositorio;
import com.blogpessoal1.blogpessoal1.service.UsuarioService;

@RestController 
@RequestMapping("/usuarios")
@CrossOrigin(origins="*",allowedHeaders="*")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	UsuarioRepositorio repository;
	
	@GetMapping ("/all")
	public ResponseEntity < List<Usuario> > getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticationUsuario(
	@RequestBody Optional<UsuarioLogin> usuario) {		
		return service.logarUsuario(usuario)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(
	@Valid @RequestBody Usuario usuario) {		
		return service.cadastroUsuario(usuario) /*verificar informação*/
		.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(
	@Valid @RequestBody Usuario usuario){		
		return service.atualizarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
}