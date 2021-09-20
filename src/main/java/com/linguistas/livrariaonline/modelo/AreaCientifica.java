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
@Table(name = "AreaCientifica")
public class AreaCientifica {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Column(columnDefinition="varchar(50)")
	public String descricao;
	
	@OneToMany(mappedBy="areaCientifica")
	public List<Livro> livros;
	
	public AreaCientifica() {}
	
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
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
}
