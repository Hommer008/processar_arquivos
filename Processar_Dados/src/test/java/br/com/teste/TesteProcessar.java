package br.com.teste;

import java.io.IOException;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import br.com.teste.dto.DadosArquivo;
import br.com.teste.excecao.ExcecaoNegocio;

public class TesteProcessar {

	@Test
	public void lerArquivoTeste() throws IOException, ExcecaoNegocio {

		DadosArquivo dados = new Processar().lerArquivo(".\\arq1.dat");

		Assert.assertEquals("Numero errado clientes", 2, dados.getClientes()
				.size());

		Assert.assertEquals("Numero errado vendas", 2, dados.getVendas().size());

		Assert.assertEquals("Numero errado vendedores", 2, dados.getVendedor()
				.size());

		Assert.assertEquals("Pior vendedor errado", "Renato",
				dados.getPiorVendedor());

		Assert.assertEquals("Numero errado vendedor ", 10, dados
				.getVendaMaisCara().intValue());

		System.out.println(dados.toString());
	}

	@Test
	public void gerarArquivoTeste() throws IOException, ExcecaoNegocio {

		DadosArquivo dados = new Processar().lerArquivo(".\\arq1.dat");

		new Processar().gerarArquivos(Collections.singletonList(dados));
	}
}
