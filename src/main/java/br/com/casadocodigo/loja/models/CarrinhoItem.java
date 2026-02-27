package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CarrinhoItem implements Serializable{

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private TipoPreco tipoPreco;

	public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
		this.produto = produto;
		this.tipoPreco = tipoPreco;
	}
	
	public BigDecimal getPreco() {
		return produto.precoPara(tipoPreco);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CarrinhoItem other))
			return false;
		return Objects.equals(produto, other.produto)
				&& tipoPreco == other.tipoPreco;
	}

	
	public BigDecimal getTotal(int quantidade) {
		return this.getPreco().multiply(new BigDecimal(quantidade));			
	}
}