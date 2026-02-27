package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@Cacheable(value = "produtosHome")
@RequestMapping("/")
public class HomeController {

	private ProdutoDAO produtoDAO;

	public HomeController(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("produtos/home");
		List<Produto> produtos = produtoDAO.listar();
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
