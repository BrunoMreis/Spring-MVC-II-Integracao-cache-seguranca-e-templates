package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarrinhoComprasControllerTest {
    @Mock
    private ProdutoDAO produtoDao;
    @Mock
    private CarrinhoCompras carrinho;
    @InjectMocks
    private CarrinhoComprasController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addDeveAdicionarItemECaminho() {
        Produto produto = new Produto();
        when(produtoDao.find(1)).thenReturn(produto);
        ModelAndView mv = controller.add(1, TipoPreco.EBOOK);
        verify(carrinho, times(1)).add(any(CarrinhoItem.class));
        assertEquals("redirect:/carrinho", mv.getViewName());
    }

    @Test
    void itensDeveRetornarView() {
        ModelAndView mv = controller.itens();
        assertEquals("carrinho/itens", mv.getViewName());
    }

    @Test
    void removerDeveRemoverItem() {
        ModelAndView mv = controller.remover(1, TipoPreco.EBOOK);
        verify(carrinho, times(1)).remover(1, TipoPreco.EBOOK);
        assertEquals("redirect:/carrinho", mv.getViewName());
    }
}
