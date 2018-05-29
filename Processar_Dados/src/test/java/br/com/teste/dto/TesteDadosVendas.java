package br.com.teste.dto;

import org.junit.Assert;
import org.junit.Test;

import br.com.teste.excecao.ExcecaoNegocio;

public class TesteDadosVendas {

	@Test
	public void testarContrutor() throws ExcecaoNegocio {
		String[] split = "003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁDiego"
				.split("Á");
		DadosVendas dados = new DadosVendas(split);

		System.out.println(dados.toString());

		Assert.assertEquals("id errado =".concat(dados.getId().toString()), 10,
				dados.getId().intValue());

		Assert.assertEquals("numero itens errado=".concat(String.valueOf(dados
				.getItens().size())), 3, dados.getItens().size());

		Assert.assertEquals(
				"nome vendedor errado=".concat(dados.getNomeVendedor()),
				"Diego", dados.getNomeVendedor());
		
	}

}
