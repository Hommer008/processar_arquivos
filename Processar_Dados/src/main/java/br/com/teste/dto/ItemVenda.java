package br.com.teste.dto;

public class ItemVenda {

	private Long id;
	private Integer quantidade;
	private Double preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", quantidade=" + quantidade
				+ ", preco=" + preco + "]";
	}
	
	
	
}
