package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;

public class CarrinhoItemTest {

     private CarrinhoItem item;
    private Produto produto;
    private Preco preco;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        preco = new Preco();

        preco.setTipo(TipoPreco.EBOOK);
        preco.setValor(new BigDecimal("100.00"));

        produto.setId(1);
        produto.setTitulo("Livro de Java");

        item = new CarrinhoItem(produto, TipoPreco.EBOOK);
    }
    
}
