package br.com.casadocodigo.loja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarrinhoComprasTest {

    private CarrinhoCompras carrinho;
    private CarrinhoItem item;
    private Produto produto;
    private Preco preco;

    @BeforeEach
    void setUp() {
        carrinho = new CarrinhoCompras();
        produto = new Produto();
        preco = new Preco();

        preco.setTipo(TipoPreco.EBOOK);
        preco.setValor(new BigDecimal("100.00"));

        produto.setId(1);
        produto.setTitulo("Livro de Java");
        produto.setPrecos(Arrays.asList(preco));

        item = new CarrinhoItem(produto, TipoPreco.EBOOK);
        carrinho.add(item);
    }

    @Test
    void deveAdicionarItemAoCarrinho() {
        assertTrue(carrinho.getItens().contains(item));
        assertEquals(1,carrinho.getQuantidade(item), "A quantidade do item deve ser 1 após a adição");
    }

    @Test
    void deveRemoverItemDoCarrinho() {
        carrinho.remover(item.getProduto().getId(), item.getTipoPreco());
        assertFalse(carrinho.getItens().contains(item));
    }

    @Test
    void deveCalcularTotal() {
        BigDecimal precoDoItem = item.getPreco();
        BigDecimal total = carrinho.getTotal();
        assertEquals(precoDoItem,total, "O total do carrinho deve ser igual ao preço do item adicionado");
    }

    @Test
    void deveCalcularQuantidadeTotal() {
        carrinho.add(item);
        int quantidade = carrinho.getQuantidade();
        assertEquals( 2,quantidade, "A quantidade total do carrinho deve ser 2 após adicionar o mesmo item novamente");
    }
}
