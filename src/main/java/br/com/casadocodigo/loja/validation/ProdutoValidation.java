package br.com.casadocodigo.loja.validation;

import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator{

	private static final String FIELD_REQUIRED = "field.required";

	@Override
	public boolean supports(@NonNull Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(@NonNull Object target,@NonNull Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", FIELD_REQUIRED);
		ValidationUtils.rejectIfEmpty(errors, "descricao", FIELD_REQUIRED);

		Produto produto = (Produto) target;
		if(produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", FIELD_REQUIRED);
		}		
	}
	
}
