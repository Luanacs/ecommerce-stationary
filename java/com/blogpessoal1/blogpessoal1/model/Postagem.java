package com.blogpessoal1.blogpessoal1.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //entidade do JPA - 
@Table (name = "tb_postagens") // do tipo tabela dou um nome à minha tabela
public class Postagem {
	


	@Id                                // chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
	private long id;
	
	@NotBlank  //not null
	@Size(min = 3, max=255)
	private String titulo;
	
	@NotBlank  //not null
	@Size( max=1000)                   //tamanho maximo
	private String texto;
	
	@UpdateTimestamp                   //vai atualizar de acordo com a data e hora atual
	private LocalDateTime data;
	
	@ManyToOne                         // relação muitos para um
	@JsonIgnoreProperties("postagem")  //usado pra tirar o looping
	private Tema tema;                 //foreign key - criou relação entre as tabelas
	
	@ManyToOne
	@JsonIgnoreProperties("usuario")  
	private Usuario usuario; 
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}


	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}





