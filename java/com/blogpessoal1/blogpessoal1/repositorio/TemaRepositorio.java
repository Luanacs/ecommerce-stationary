package com.blogpessoal1.blogpessoal1.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpessoal1.blogpessoal1.model.Tema;

public interface TemaRepositorio extends JpaRepository <Tema,Long> {
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}