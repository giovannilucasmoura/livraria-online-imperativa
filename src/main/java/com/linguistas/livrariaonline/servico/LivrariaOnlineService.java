package com.linguistas.livrariaonline.servico;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linguistas.livrariaonline.modelo.AreaCientifica;
import com.linguistas.livrariaonline.modelo.Autor;
import com.linguistas.livrariaonline.modelo.Cliente;
import com.linguistas.livrariaonline.modelo.Compra;
import com.linguistas.livrariaonline.modelo.Livro;
import com.linguistas.livrariaonline.repo.AreaCientificaDAO;
import com.linguistas.livrariaonline.repo.AutorDAO;
import com.linguistas.livrariaonline.repo.ClienteDAO;
import com.linguistas.livrariaonline.repo.CompraDAO;
import com.linguistas.livrariaonline.repo.LivroDAO;

@Component
public class LivrariaOnlineService {
	@Autowired
	LivroDAO livroDAO;
	
	@Autowired
	AutorDAO autorDAO;
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	CompraDAO compraDAO;
	
	@Autowired
	AreaCientificaDAO areaCientificaDAO;
	public List<Compra> listarVendasPorPublicacao(Livro publicacao){
		List<Compra> compras = compraDAO.findAll();
		
		// Removendo livros com editoras diferentes da pedida para as compras
		Iterator<Compra> iteratorCompras = compras.iterator();
		while(iteratorCompras.hasNext()) {
			Compra compra = iteratorCompras.next();
			Iterator<Livro> iteratorLivro = compra.getLivros().iterator();
			
			while(iteratorLivro.hasNext()) {
				Livro livro = iteratorLivro.next();
				
				if(livro.getId() != publicacao.getId()) {
					iteratorLivro.remove();
				}
			}
		}
		
		// Removendo compras que não tem nenhum livro restante
		iteratorCompras = compras.iterator();
		while(iteratorCompras.hasNext()) {
			Compra compra = iteratorCompras.next();
			
			if(compra.getLivros().isEmpty()) {
				iteratorCompras.remove();
			}
		}
		
		return compras;
	}

	
	
	public Map<Long, String> getMapLivros() {
		Map<Long, String> mapLivros = new HashMap<Long, String>();
		List<Livro> listaLivros = livroDAO.findAll();
		mapLivros.put(-1L, "-- Selecione --");
		
		for(Livro livro : listaLivros) {
			mapLivros.put(livro.getId(), livro.getTitulo());
		}
		
		return mapLivros;
	}

	public List<Livro> listarPublicacoesPorAutor(Autor autor){
		List<Livro> livros = livroDAO.findAll();

		// Removendo livros diferentes do solicitado para a listagem
		Iterator<Livro> iteratorLivros = livros.iterator();
		while(iteratorLivros.hasNext()) {
			Livro livro = iteratorLivros.next();

			Iterator<Autor> iteratorAutores = livro.getAutores().iterator();
			while(iteratorAutores.hasNext()) {
				Autor autorIter = iteratorAutores.next();
				if(autor.getId() != autorIter.getId()) {
					iteratorAutores.remove();
				}
			}
		}
			
		// Removendo autor que não tem nenhum livro restante
		iteratorLivros = livros.iterator();
		while(iteratorLivros.hasNext()) {
			Livro livro = iteratorLivros.next();
			
			if(livro.getAutores().isEmpty()) {
				iteratorLivros.remove();
			}
		}
		
		return livros;
	}
	
	public Map<Long, String> getMapAutores() {
		Map<Long, String> mapAutores = new HashMap<Long, String>();
		List<Autor> listaAutores = autorDAO.findAll();
		mapAutores.put(-1L, "-- Selecione --");
		
		for(Autor autor : listaAutores) {
			mapAutores.put(autor.getId(), autor.getNome());
		}
		
		return mapAutores;
	}

	public List<Compra> listarComprasPorMes(String dataString) throws ParseException{
		List<Compra> compras = compraDAO.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
		YearMonth ym = YearMonth.parse(dataString, formatter);
		LocalDate data = ym.atDay(1);
		data.plusMonths(1);
		
		// Removendo livros com datas diferentes da pedida para a consulta
		Iterator<Compra> iteratorCompras = compras.iterator();
		while(iteratorCompras.hasNext()) {
			Compra compra = iteratorCompras.next();
			
			if(compra.getData().getMonth() != data.getMonth() ||
					compra.getData().getYear() != data.getYear()) {
				iteratorCompras.remove();
			}
		}
		
		return compras;
	}

	public List<Compra> listarComprasPorClientes(Cliente clt){
		List<Compra> compras = compraDAO.findAll();
		
		// Removendo compras que não sejam do cliente solicitado
		Iterator<Compra> iteratorCompras = compras.iterator();
		while(iteratorCompras.hasNext()) {
			Compra compra = iteratorCompras.next();
			
			if (!compra.getCliente().equals(clt) ) {
				iteratorCompras.remove();
			}
		}
		return compras;
	}
	
	public Map<LocalDate, String> getMapCompras() {
		Map<LocalDate, String> listaDatas = new HashMap<LocalDate, String>();
		List<Compra> listaCompras = compraDAO.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
		listaDatas.put(null, null);
		for(Compra compra : listaCompras) {

			listaDatas.put(compra.getData(), compra.getData().format(formatter));

		}

		return listaDatas;
	}
	
	public List<String> getListaMeses() {
		List<Compra> listaCompras = compraDAO.findAll();
		List<String> listaMeses = new LinkedList<String>();
		
		for(Compra compra : listaCompras) {
			if(compra.getData().getMonthValue() < 10) listaMeses.add("0" + compra.getData().getMonthValue() + "/" + compra.getData().getYear());
			else listaMeses.add(compra.getData().getMonthValue() + "/" + compra.getData().getYear());
		}
		
		listaMeses = listaMeses.stream().distinct().collect(Collectors.toList());
		
		return listaMeses;
	}
	
	public Map<Long, String> getMapClientes() {
		Map<Long, String> mapClientes = new HashMap<Long, String>();
		List<Cliente> listaClientes = clienteDAO.findAll();
		mapClientes.put(-1L, "-- Selecione --");
		
		for(Cliente cliente : listaClientes) {
			mapClientes.put(cliente.getId(), cliente.getNome());
		}
		
		return mapClientes;
	}
	
	public List<Livro> listarPublicacoesPorDominio(AreaCientifica dominio){
		
		return dominio.getLivros();
	}
	
	public Map<Long, String> getMapDominios() {
		Map<Long, String> mapDominios = new HashMap<Long, String>();
		List<AreaCientifica> listaDominios = areaCientificaDAO.findAll();
		mapDominios.put(-1L, "-- Selecione --");
		
		for(AreaCientifica areaCientifica : listaDominios) {
			mapDominios.put(areaCientifica.getId(), areaCientifica.getDescricao());
		}
		
		return mapDominios;
	}
}