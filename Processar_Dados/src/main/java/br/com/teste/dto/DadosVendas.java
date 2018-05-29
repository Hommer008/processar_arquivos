package br.com.teste.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.teste.excecao.ExcecaoNegocio;

public class DadosVendas {

	private Long id;

	private List<ItemVenda> itens;

	private String nomeVendedor;

	private Double valorTotal;

	public DadosVendas() {
	}

	public DadosVendas(String[] dadosLinha) throws ExcecaoNegocio {
		try {
			id = Long.valueOf(dadosLinha[1]);
			itens = new ArrayList<ItemVenda>();

			String seguenciaCompras = dadosLinha[2].substring(1, dadosLinha[2].length() - 1);

			for (String vendas : seguenciaCompras.split(",")) {
				ItemVenda venda = new ItemVenda();
				String[] valores = vendas.split("-");
				venda.setId(Long.valueOf(valores[0]));
				venda.setQuantidade(Integer.valueOf(valores[1]));
				venda.setPreco(Double.valueOf(valores[2]));
				itens.add(venda);
			}

			nomeVendedor = dadosLinha[3];

			calcularTotal();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExcecaoNegocio("Erro ao ler dados Vendas", ex);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "DadosVendas [id=" + id + ", itens=" + itens + ", nomeVendedor=" + nomeVendedor + ", valorTotal="
				+ valorTotal + "]";
	}

	private void calcularTotal() {
		valorTotal = new Double(0);
		for (ItemVenda itemVenda : itens) {
			valorTotal += itemVenda.getPreco() * itemVenda.getQuantidade();
		}
	}

}
