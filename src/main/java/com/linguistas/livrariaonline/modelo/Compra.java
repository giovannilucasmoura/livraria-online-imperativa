package com.linguistas.livrariaonline.modelo;
import java.time.LocalDate;
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
@Table(name = "Compra")
public class Compra {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ManyToOne
	public Cliente cliente;
	
	@ManyToMany
	@JoinTable(name = "compra_livro",
	  joinColumns = @JoinColumn(name = "compra_id"),
	  inverseJoinColumns = @JoinColumn(name = "livro_id"))
	public List<Livro> livros;
	
	@Column(columnDefinition="date")
	public LocalDate data;
	
	public Compra() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
