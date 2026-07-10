package br.com.casadocodigo.loja.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.casadocodigo.loja.models.Produto;

class ProdutoValidationTest {

    private final ProdutoValidation validator = new ProdutoValidation();

    @Test
    void shouldSupportProdutoInstances() {
        assertTrue(validator.supports(Produto.class));
        assertFalse(validator.supports(String.class));
    }

    @Test
    void shouldRejectMissingRequiredFieldsAndInvalidPages() {
        Produto produto = new Produto();
        produto.setTitulo("");
        produto.setDescricao("");
        produto.setPaginas(0);

        Errors errors = new BeanPropertyBindingResult(produto, "produto");

        validator.validate(produto, errors);

        assertTrue(errors.hasFieldErrors("titulo"));
        assertTrue(errors.hasFieldErrors("descricao"));
        assertTrue(errors.hasFieldErrors("paginas"));
        assertEquals("field.required", errors.getFieldError("titulo").getCode());
        assertEquals("field.required", errors.getFieldError("descricao").getCode());
        assertEquals("field.required", errors.getFieldError("paginas").getCode());
    }

    @Test
    void shouldAcceptValidProduto() {
        Produto produto = new Produto();
        produto.setTitulo("Spring MVC");
        produto.setDescricao("Livro de Spring MVC");
        produto.setPaginas(250);

        Errors errors = new BeanPropertyBindingResult(produto, "produto");

        validator.validate(produto, errors);

        assertFalse(errors.hasErrors());
        assertFalse(errors.hasFieldErrors("titulo"));
        assertFalse(errors.hasFieldErrors("descricao"));
        assertFalse(errors.hasFieldErrors("paginas"));
    }
}
