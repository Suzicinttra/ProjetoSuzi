package com.app.desafio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @NotNull @Size(min = 2, max = 20, message = "Erro no tamanho do nome") String nome;
	private @Email @NotNull String email;
	
	
	private @NotNull @Size(min = 2, max = 20, message = "Erro no tamanho do usuário") String usuario;
	private @NotNull @Size(min = 2, message = "Erro no tamanho da senha") String senha;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}	

}
