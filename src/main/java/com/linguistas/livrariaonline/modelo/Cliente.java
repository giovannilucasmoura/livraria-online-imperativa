package com.linguistas.livrariaonline.modelo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Column(columnDefinition="varchar(100)")
	public String nome;
	
	@Column(columnDefinition="varchar(100)")
	public String apelido;
	
	@Column(columnDefinition="varchar(30)")
	public String nacionalidade;
	
	@Column(columnDefinition="varchar(100)")
	public String email;
	
	@Column(columnDefinition="varchar(30)")
	public String senha;
	
	@Column(columnDefinition="varchar(30)")
	public String numeroCartaoCredito;
	
	@Column(columnDefinition="varchar(30)")
	public String profissao;
	
	@Column(columnDefinition="varchar(100)")
	public String endereco;
	
	@Column(columnDefinition="varchar(30)")
	public String telefone;
	
	@OneToMany(mappedBy="cliente")
	public List<Compra> compras;
	
	public Cliente () {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNumeroCartaoCredito() {
		return numeroCartaoCredito;
	}
	public String setNumeroCartaoCredito(String numeroCartaoCredito) {
		return this.numeroCartaoCredito = numeroCartaoCredito;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Compra> getCompras() {
		return compras;
	}
	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public String toString() {
		return nome;
	}
}
