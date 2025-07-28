package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@Cacheable(value = "produtosHome")
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("produtos/home");
		List<Produto> produtos = produtoDAO.listar();
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
