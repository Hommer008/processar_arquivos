package br.com.teste.dto;

import br.com.teste.excecao.ExcecaoNegocio;

public class DadosCliente {

	private String cnpj;

	private String nome;

	private String area;

	public DadosCliente() {
	}

	public DadosCliente(String[] dadosLinha) throws ExcecaoNegocio {
		try {
			cnpj = dadosLinha[1];
			nome = dadosLinha[2];
			area = dadosLinha[3];
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExcecaoNegocio("Erro ao ler dados Cliente", ex);
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "DadosCliente [cnpj=" + cnpj + ", nome=" + nome + ", area=" + area + "]";
	}

}
