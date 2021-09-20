package com.linguistas.livrariaonline.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Livro")
public class Livro {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Column(columnDefinition="varchar(255)")
	public String titulo;
	
	@Column(columnDefinition="varchar(30)")
	public String ISBN;
	
	@ManyToMany
	@JoinTable(name = "livro_autores", 
			  joinColumns = @JoinColumn(name = "livro_id"), 
			  inverseJoinColumns = @JoinColumn(name = "autor_id"))
	public List<Autor> autores;
	
	@Column(columnDefinition="varchar(50)")
	public String editora;
	
	@Column(columnDefinition="int")
	public int anoPublicacao;
	
	@Column(columnDefinition="float default 0.0")
	public float preco;
	
	@ManyToOne
	public AreaCientifica areaCientifica;
	
	public Livro() {}
	
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
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public AreaCientifica getAreaCientifica() {
		return areaCientifica;
	}
	public void setAreaCientifica(AreaCientifica areaCientifica) {
		this.areaCientifica = areaCientifica;
	}

	@Override
	public String toString() {
		return titulo;
	}
	
}
