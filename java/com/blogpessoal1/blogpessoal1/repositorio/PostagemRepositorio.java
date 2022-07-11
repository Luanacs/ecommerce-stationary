package com.blogpessoal1.blogpessoal1.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogpessoal1.blogpessoal1.model.Postagem;

@Repository
public interface PostagemRepositorio extends JpaRepository <Postagem,Long> {
	 
	public List<Postagem>findAllByTituloContainingIgnoreCase(@Param ("titulo")String titulo);
	
	
}
