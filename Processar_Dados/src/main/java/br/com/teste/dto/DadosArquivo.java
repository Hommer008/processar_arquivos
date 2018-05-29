package br.com.teste.dto;

import java.util.ArrayList;
import java.util.List;

public class DadosArquivo {

	private String nomeArquivo;
	/**
	 * codigo 001
	 */
	private List<DadosVendedor> vendedor;
	/**
	 * codigo 002
	 */
	private List<DadosCliente> clientes;
	/**
	 * codigo 003
	 */
	private List<DadosVendas> vendas;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public List<DadosCliente> getClientes() {
		if (clientes == null) {
			clientes = new ArrayList<DadosCliente>();
		}
		return clientes;
	}

	public void setClientes(List<DadosCliente> clientes) {
		this.clientes = clientes;
	}

	public List<DadosVendedor> getVendedor() {
		if (vendedor == null) {
			vendedor = new ArrayList<DadosVendedor>();
		}
		return vendedor;
	}

	public void setVendedor(List<DadosVendedor> vendedor) {
		this.vendedor = vendedor;
	}

	public List<DadosVendas> getVendas() {
		if (vendas == null) {
			vendas = new ArrayList<DadosVendas>();
		}
		return vendas;
	}

	public void setVendas(List<DadosVendas> vendas) {
		this.vendas = vendas;
	}

	public String getPiorVendedor() {
		DadosVendedor piorVendedor = null;
		if (getVendedor().isEmpty()) {
			return null;
		}
		for (DadosVendedor dadosVendedor : getVendedor()) {
			if (piorVendedor == null) {
				piorVendedor = dadosVendedor;
			} else {
				if (piorVendedor.getAcumuladorVendas() > dadosVendedor.getAcumuladorVendas()) {
					piorVendedor = dadosVendedor;
				}
			}
		}
		return piorVendedor.getNome();
	}

	public Long getVendaMaisCara() {
		DadosVendas vendaMaisCara = null;
		if (getVendas().isEmpty()) {
			return null;
		}
		for (DadosVendas dadosVendas : getVendas()) {
			if (vendaMaisCara == null) {
				vendaMaisCara = dadosVendas;
			} else {
				if (vendaMaisCara.getValorTotal() < dadosVendas.getValorTotal()) {
					vendaMaisCara = dadosVendas;
				}
			}
		}
		return vendaMaisCara.getId();
	}

	@Override
	public String toString() {
		return "DadosArquivo [nomeArquivo=" + nomeArquivo + ", vendedor=" + vendedor + ", clientes=" + clientes
				+ ", vendas=" + vendas + "]";
	}
}
