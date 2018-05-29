package br.com.teste.excecao;

public class ExcecaoNegocio extends Exception {

	public ExcecaoNegocio(String mensagem, Exception ex) {
		super(mensagem, ex);
	}
}
