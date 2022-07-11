package com.blogpessoal1.blogpessoal1.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.blogpessoal1.blogpessoal1.model.Usuario;
import com.blogpessoal1.blogpessoal1.model.UsuarioLogin;
import com.blogpessoal1.blogpessoal1.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public Optional<Usuario>cadastroUsuario(Usuario usuario){
		if (usuarioRepositorio.findByUsuario(usuario.getUsuario())
				.isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
				"Usuário já existe!", null);
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepositorio.save(usuario));
	}
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepositorio.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepositorio.
			findByUsuario(usuario.getUsuario());

			if (buscaUsuario.isPresent()) {				
				if (buscaUsuario.get().getId() != usuario.getId())
				  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					 "Usuário não existe", null);
			}
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(usuarioRepositorio.save(usuario));
		} 
			
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
		"Usuário não encontrado!", null);		
	}	
	public Optional<UsuarioLogin> logarUsuario(
			Optional<UsuarioLogin> usuarioLogin) {
			
			Optional<Usuario> usuario = usuarioRepositorio
			.findByUsuario(usuarioLogin.get().getUsuario());

			if (usuario.isPresent()) {
				if (compararSenhas(usuarioLogin.get().getSenha(), 
					usuario.get().getSenha())) {

					usuarioLogin.get().setId(usuario.get().getId());				
					usuarioLogin.get().setNome(usuario.get().getNome());
					usuarioLogin.get().setFoto(usuario.get().getFoto());
					usuarioLogin.get().setTipo(usuario.get().getTipo());
					usuarioLogin.get().setToken(
					gerarBasicToken(usuarioLogin.get().getUsuario(), 
					usuarioLogin.get().getSenha()));
					usuarioLogin.get().setSenha(usuario.get().getSenha());

					return usuarioLogin;

				}
			}		
			
			throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
		}
		

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);

		return senhaEncoder;
	}
	
	private boolean compararSenhas(String senhaDigitada, 
	String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);		
	}
	
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}


