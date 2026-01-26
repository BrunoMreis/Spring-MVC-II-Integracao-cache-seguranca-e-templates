package br.com.casadocodigo.loja.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;

class ProdutosControllerTest {

    @Mock
    private ProdutoDAO dao;
    @Mock
    private FileSaver fileSaver;
    @Mock
    private BindingResult result;
    @Mock
    private MultipartFile file;
    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private ProdutosController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void formDeveRetornarViewECriarTipos() {
        ModelAndView mv = controller.form(new Produto());
        assertEquals("/produtos/form", mv.getViewName());
        assertTrue(mv.getModel().containsKey("tipos"));
    }

    @Test
    void gravarComErroDeveRetornarForm() {
        when(result.hasErrors()).thenReturn(true);
        ModelAndView mv = controller.gravar(file, new Produto(), result, redirectAttributes);
        assertEquals("/produtos/form", mv.getViewName());
    }

    @Test
    void gravarComSucessoDeveRedirecionar() {
        when(result.hasErrors()).thenReturn(false);
        when(fileSaver.write(anyString(), any())).thenReturn("caminho");
        ModelAndView mv = controller.gravar(file, new Produto(), result, redirectAttributes);
        assertEquals("redirect:produtos", mv.getViewName());
        verify(dao, times(1)).gravar(any());
    }

    @Test
    void listarDeveRetornarProdutos() {
        when(dao.listar()).thenReturn(Collections.emptyList());
        ModelAndView mv = controller.listar();
        assertEquals("/produtos/lista", mv.getViewName());
        assertTrue(mv.getModel().containsKey("produtos"));
    }

    @Test
    void detalheDeveRetornarProduto() {
        Produto produto = new Produto();
        when(dao.find(1)).thenReturn(produto);
        ModelAndView mv = controller.detalhe(1);
        assertEquals("/produtos/detalhe", mv.getViewName());
        assertEquals(produto, mv.getModel().get("produto"));
    }
}
