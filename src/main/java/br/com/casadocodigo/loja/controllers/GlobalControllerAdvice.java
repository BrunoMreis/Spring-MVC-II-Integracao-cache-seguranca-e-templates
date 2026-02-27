package br.com.casadocodigo.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.casadocodigo.loja.models.CarrinhoCompras;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final CarrinhoCompras carrinhoCompras;

    public GlobalControllerAdvice(CarrinhoCompras carrinhoCompras) {
        this.carrinhoCompras = carrinhoCompras;
    }

    @ModelAttribute("carrinhoCompras")
    public CarrinhoCompras carrinhoCompras() {
        return carrinhoCompras;
    }
}
