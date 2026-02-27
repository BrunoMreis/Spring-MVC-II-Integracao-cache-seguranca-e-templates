package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoController .class);
	
	private CarrinhoCompras carrinho;
	
	private RestTemplate restTemplate;

	public PagamentoController(CarrinhoCompras carrinho, RestTemplate restTemplate) {
		this.carrinho = carrinho;
		this.restTemplate = restTemplate;
	}
	
	@PostMapping("/finalizar")
	public Callable<ModelAndView>  finalizar(RedirectAttributes model){
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				model.addFlashAttribute("sucesso", response);
				LOGGER.debug("Response: {}", response);
				return new ModelAndView("redirect:/produtos");
			} catch (HttpClientErrorException e) {
				LOGGER.warn("Erro ao processar pagamento: {}", e.getMessage(), e);
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}
}