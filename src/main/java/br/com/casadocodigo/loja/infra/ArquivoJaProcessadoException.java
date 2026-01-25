package br.com.casadocodigo.loja.infra;

public class ArquivoJaProcessadoException extends RuntimeException {
    public ArquivoJaProcessadoException(String message, Throwable cause) {
        super(message, cause);
    }
}