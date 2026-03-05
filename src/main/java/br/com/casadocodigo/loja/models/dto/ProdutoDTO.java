package br.com.casadocodigo.loja.models.dto;

import br.com.casadocodigo.loja.models.Produto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * DTO de entrada para criação/atualização de Produto.
 * Contém apenas os campos que o cliente pode alterar.
 */
public record ProdutoDTO(
        @NotBlank(message = "titulo é obrigatório")
        @Size(max = 255, message = "titulo deve ter no máximo 255 caracteres")
        String titulo,

        @NotBlank(message = "descricao é obrigatória")
        @Size(max = 4000, message = "descricao deve ter no máximo 4000 caracteres")
        String descricao,

        @Min(value = 1, message = "paginas deve ser pelo menos 1")
        Integer paginas
) {
    /**
     * Fábrica para criar um ProdutoDTO a partir de campos simples.
     */
    public static ProdutoDTO of(String titulo, String descricao, Integer paginas) {
        return new ProdutoDTO(titulo, descricao, paginas);
    }

    /**
     * Cria uma nova instância de {@link Produto} populando apenas os campos permitidos.
     * Campos gerenciados pelo servidor (id, sumarioPath, precos, dataLancamento) não são alterados aqui.
     */
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setTitulo(this.titulo);
        produto.setDescricao(this.descricao);
        produto.setPaginas(this.paginas != null ? this.paginas : 0);
        return produto;
    }

    /**
     * Aplica os valores deste DTO em uma entidade {@link Produto} existente.
     * Apenas os campos permitidos são atualizados; campos sensíveis permanecem inalterados.
     *
     * @param produto entidade a ser atualizada (não nula)
     * @return a mesma instância de produto, com campos atualizados
     * @throws NullPointerException se produto for nulo
     */
    public Produto applyTo(Produto produto) {
        Objects.requireNonNull(produto, "produto não pode ser null");
        produto.setTitulo(this.titulo);
        produto.setDescricao(this.descricao);
        if (this.paginas != null) {
            produto.setPaginas(this.paginas);
        }
        return produto;
    }

    /**
     * Cria uma nova instância de {@link ProdutoDTO} a partir de uma entidade {@link Produto}.
     *
     * @param p entidade de origem
     * @return uma nova instância de {@link ProdutoDTO}
     */
    public static ProdutoDTO fromEntity(Produto p) {
        return new ProdutoDTO(p.getTitulo(), p.getDescricao(), p.getPaginas());
    }

}