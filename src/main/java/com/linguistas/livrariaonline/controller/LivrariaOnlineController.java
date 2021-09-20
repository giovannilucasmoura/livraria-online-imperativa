package com.linguistas.livrariaonline.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
import com.linguistas.livrariaonline.servico.LivrariaOnlineService;

@Controller
public class LivrariaOnlineController {
	
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
	
	@Autowired
	LivrariaOnlineService livrariaOnlineService;
	
	@RequestMapping(value={"/", "", "main"})
	public ModelAndView main(String nome) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("nome", nome);
		mv.setViewName("main");
		
		return mv;
	}
	
	@RequestMapping("listagem")
	public ModelAndView listagem() {
		ModelAndView mv = new ModelAndView();
		ArrayList<Livro> listaLivros = (ArrayList<Livro>) livroDAO.findByOrderByIdAsc();
		ArrayList<Autor> listaAutores = (ArrayList<Autor>) autorDAO.findByOrderByIdAsc();
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) clienteDAO.findByOrderByIdAsc();
		ArrayList<Compra> listaCompras = (ArrayList<Compra>) compraDAO.findByOrderByIdAsc();
		ArrayList<AreaCientifica> listaAreasCientificas = (ArrayList<AreaCientifica>) areaCientificaDAO.findByOrderByIdAsc();
		
		mv.addObject("livros", listaLivros);
		mv.addObject("autores", listaAutores);
		mv.addObject("clientes", listaClientes);
		mv.addObject("compras", listaCompras);
		mv.addObject("areasCientificas", listaAreasCientificas);
		
		mv.setViewName("listagem");
		
		return mv;
	}
	
	@RequestMapping("vendasPorPublicacao")
	public ModelAndView vendasPorPublicacao(@RequestParam(name="publicacaoId", required=false) 
		Long publicacaoId) {
		ModelAndView mv = new ModelAndView();
		List<Compra> listaCompras = new ArrayList<Compra>();
		Map<Long, String> opcoesLivros = livrariaOnlineService.getMapLivros();
		
		if(publicacaoId == null || publicacaoId <= 0) {
			listaCompras = compraDAO.findAll();
		} else {
			Livro publicacao = livroDAO.getById(publicacaoId);
			listaCompras = livrariaOnlineService.listarVendasPorPublicacao(publicacao);
		}
		
		if(publicacaoId != null && publicacaoId >= 0) {
			mv.addObject("publicacaoId", publicacaoId);
		}
		mv.addObject("livros", opcoesLivros);
		mv.addObject("compras", listaCompras);
		
		mv.setViewName("vendasPorPublicacao");

		return mv;
	}

	@RequestMapping("publicacoesPorAutor")
	public ModelAndView publicacoesPorAutor(@RequestParam(name="autorId", required=false) 
		Long autorId) {
		ModelAndView mv = new ModelAndView();
		List<Livro> listaLivros = new ArrayList<Livro>();
		Map<Long, String> opcoesAutores = livrariaOnlineService.getMapAutores();

		if(autorId == null || autorId <= 0) {
			listaLivros = livroDAO.findAll();
		} else {
			Autor autor = autorDAO.getById(autorId);
			listaLivros = livrariaOnlineService.listarPublicacoesPorAutor(autor);
		}
		
		if(autorId != null && autorId >= 0) {
			mv.addObject("autorId", autorId);
		}
		mv.addObject("autores", opcoesAutores);
		mv.addObject("livros", listaLivros);
		
		mv.setViewName("publicacoesPorAutor");
		
		return mv;
	}

	@RequestMapping("vendasPorMes")
	public ModelAndView vendasPorMes(@RequestParam(name="publicacaoId", required=false) 
		String data) throws ParseException {
		ModelAndView mv = new ModelAndView();
		List<Compra> listaCompras = new ArrayList<Compra>();
		List<String> opcoesDatas = livrariaOnlineService.getListaMeses();
		
		if(data == null || data.isEmpty()) {
			listaCompras = compraDAO.findAll();
		} else {
			listaCompras = livrariaOnlineService.listarComprasPorMes(data);
		}
		
		if(data != null) {
			mv.addObject("publicacaoId", data);
		}
		mv.addObject("livros", opcoesDatas);
		mv.addObject("compras", listaCompras);
			
		mv.setViewName("vendasPorMes");
			
		return mv;
	}

	
	@RequestMapping("comprasPorCliente")
	public ModelAndView comprasPorCliente(@RequestParam(name="clienteId", required=false) 
		Long clienteId) {
		ModelAndView mv = new ModelAndView();
		List<Compra> listaCompras = new ArrayList<Compra>();
		Map<Long, String> opcoesClientes = livrariaOnlineService.getMapClientes();
		
		if(clienteId == null || clienteId <= 0) {
			listaCompras = compraDAO.findAll();
		} else {
			Cliente cliente = clienteDAO.getById(clienteId);
			listaCompras = livrariaOnlineService.listarComprasPorClientes(cliente);
		}
		
		if(clienteId != null && clienteId >= 0) {
			mv.addObject("publicacaoId", clienteId);
		}
		mv.addObject("clientes", opcoesClientes);
		mv.addObject("compras", listaCompras);
		
		mv.setViewName("comprasPorCliente");
		
		return mv;
	}
	
	@RequestMapping("publicacoesPorDominio")
	public ModelAndView publicacoesPorDominio(@RequestParam(name="dominioId", required=false) 
		Long dominioId) {
		ModelAndView mv = new ModelAndView();
		List<Livro> listaLivros = new ArrayList<Livro>();
		Map<Long, String> opcoesDominios = livrariaOnlineService.getMapDominios();
		
		if(dominioId == null || dominioId <= 0) {
			listaLivros = livroDAO.findAll();
		} else {
			AreaCientifica dominio = areaCientificaDAO.getById(dominioId);
			listaLivros = livrariaOnlineService.listarPublicacoesPorDominio(dominio);
		}
		
		if(dominioId != null && dominioId >= 0) {
			mv.addObject("publicacaoId", dominioId);
		}
		mv.addObject("dominios", opcoesDominios);
		mv.addObject("livros", listaLivros);
		
		mv.setViewName("publicacoesPorDominio");
		
		return mv;
	}
}
