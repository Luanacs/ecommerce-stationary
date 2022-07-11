package com.blogpessoal1.blogpessoal1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity                    //entidade de tabela
@Table (name = "tb_temas") // dou um nome à minha tabela
public class Tema {
	
	@Id  // chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
	private long id;
	
	@NotBlank  //not null
	@Size( max=1000) //tamanho maximo
	private String descricao;
	
	@OneToMany(mappedBy= "tema", cascade = CascadeType.REMOVE) //vai remover somente o que está relacionado ao tema
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	
}