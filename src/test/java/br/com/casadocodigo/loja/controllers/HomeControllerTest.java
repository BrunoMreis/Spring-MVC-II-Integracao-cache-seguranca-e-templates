package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {
    @Mock
    private ProdutoDAO produtoDAO;
    @InjectMocks
    private HomeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void indexDeveRetornarProdutosNaHome() {
        when(produtoDAO.listar()).thenReturn(Collections.singletonList(new Produto()));
        ModelAndView mv = controller.index();
        assertEquals("produtos/home", mv.getViewName());
        assertTrue(mv.getModel().containsKey("produtos"));
    }
}
