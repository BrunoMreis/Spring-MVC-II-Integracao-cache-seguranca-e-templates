package br.com.casadocodigo.loja.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.casadocodigo.loja.models.CarrinhoCompras;


class GlobalControllerAdviceIntegrationTest {


    @Test
    void deveRetornarCarrinhoComprasNoModelAttribute() {
        CarrinhoCompras carrinho = new CarrinhoCompras();
        GlobalControllerAdvice advice = new GlobalControllerAdvice(carrinho);

        CarrinhoCompras resultado = advice.carrinhoCompras();

        assertThat(resultado).isSameAs(carrinho);
    }
}
