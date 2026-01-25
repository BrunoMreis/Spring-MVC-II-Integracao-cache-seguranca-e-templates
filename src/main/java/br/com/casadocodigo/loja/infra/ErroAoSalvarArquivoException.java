package br.com.casadocodigo.loja.infra;

public class ErroAoSalvarArquivoException extends RuntimeException {
    public ErroAoSalvarArquivoException(String message, Throwable cause) {
        super(message, cause);
    }
}
