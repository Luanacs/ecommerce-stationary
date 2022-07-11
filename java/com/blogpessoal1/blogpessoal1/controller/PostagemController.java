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

import com.blogpessoal1.blogpessoal1.model.Postagem;
import com.blogpessoal1.blogpessoal1.repositorio.PostagemRepositorio;
import com.blogpessoal1.blogpessoal1.repositorio.TemaRepositorio;
import com.blogpessoal1.blogpessoal1.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/postagens") // receber requisições
@CrossOrigin(origins = "*", allowedHeaders = "*") // Pode ser "consumido" de qualquer origem
public class PostagemController {

	@Autowired //
	private PostagemRepositorio postagensRepositorio;

	@Autowired
	private TemaRepositorio temasRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagensRepositorio.findAll());

	}

	@GetMapping("/{id}") // método para acessar
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagensRepositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/titulo/{titulo}") // o ultimo dado de depois do / é um pathvariable, ele entende como um atributo
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagensRepositorio.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagens) {
		if (temasRepositorio.existsById(postagens.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(postagensRepositorio.save(postagens));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagens) {
		if (postagensRepositorio.existsById(postagens.getId())) {
			if (temasRepositorio.existsById(postagens.getTema().getId())) {
				if (usuarioRepositorio.existsById(postagens.getUsuario().getId())) {
					return ResponseEntity.status(HttpStatus.OK).body(postagensRepositorio.save(postagens));
				}
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable Long id) {

		return postagensRepositorio.findById(id).map(resposta -> {
			postagensRepositorio.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
}