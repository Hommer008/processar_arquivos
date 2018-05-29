package br.com.teste.dto;

import br.com.teste.excecao.ExcecaoNegocio;

public class DadosVendedor {

	private String cpf;
	private String nome;
	private String salario;

	private Double acumuladorVendas;

	public DadosVendedor() {
	}

	public DadosVendedor(String[] dados) throws ExcecaoNegocio {
		try {
			cpf = dados[1];
			nome = dados[2];
			salario = dados[3];
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExcecaoNegocio("Erro ao ler dados Vendedor", ex);
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public Double getAcumuladorVendas() {
		if (acumuladorVendas == null) {
			acumuladorVendas = new Double(0);
		}
		return acumuladorVendas;
	}

	public void setAcumuladorVendas(Double acumuladorVendas) {
		this.acumuladorVendas = acumuladorVendas;
	}

	@Override
	public String toString() {
		return "DadosVendedor [cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + ", acumuladorVendas="
				+ acumuladorVendas + "]";
	}

}
