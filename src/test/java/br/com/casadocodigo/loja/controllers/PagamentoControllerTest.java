package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {
    @Mock
    private CarrinhoCompras carrinho;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private RedirectAttributes redirectAttributes;
    @InjectMocks
    private PagamentoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void finalizarComSucessoDeveRedirecionar() throws Exception {
        when(carrinho.getTotal()).thenReturn(new BigDecimal("10.00"));
        when(restTemplate.postForObject(anyString(), any(DadosPagamento.class), eq(String.class))).thenReturn("Pagamento realizado");
        Callable<ModelAndView> callable = controller.finalizar(redirectAttributes);
        ModelAndView mv = callable.call();
        assertEquals("redirect:/produtos", mv.getViewName());
        verify(redirectAttributes).addFlashAttribute(eq("sucesso"), any());
    }

    @Test
    void finalizarComErroDeveRedirecionarComFalha() throws Exception {
        when(carrinho.getTotal()).thenReturn(new BigDecimal("10000.00"));
        when(restTemplate.postForObject(anyString(), any(DadosPagamento.class), eq(String.class))).thenThrow(HttpClientErrorException.class);
        Callable<ModelAndView> callable = controller.finalizar(redirectAttributes);
        ModelAndView mv = callable.call();
        assertEquals("redirect:/produtos", mv.getViewName());
        verify(redirectAttributes).addFlashAttribute(eq("falha"), any());
    }
}
