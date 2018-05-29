package br.com.teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import br.com.teste.dto.DadosArquivo;
import br.com.teste.dto.DadosCliente;
import br.com.teste.dto.DadosVendas;
import br.com.teste.dto.DadosVendedor;
import br.com.teste.excecao.ExcecaoNegocio;

public class Processar {

	private static final String codigoVendedor = "001";
	private static final String codigoCliente = "002";
	private static final String codigoVenda = "003";

	protected void gerarArquivos(List<DadosArquivo> dadosArquivos) throws IOException {

		File file = new File("./out/");
		if (!file.exists()) {
			file.mkdir();
		}

		for (DadosArquivo dadosArquivo : dadosArquivos) {

			String nomeArquivoSaida = dadosArquivo.getNomeArquivo().substring(0,
					dadosArquivo.getNomeArquivo().lastIndexOf("."));
			nomeArquivoSaida = nomeArquivoSaida.concat(".done.dat.");
			FileWriter arq = new FileWriter("./out/" + nomeArquivoSaida);

			PrintWriter gravarArq = new PrintWriter(arq);

			StringBuilder msg = new StringBuilder();

			msg.append(dadosArquivo.getClientes().size());
			msg.append(" %n");
			gravarArq.printf(msg.toString());

			msg = new StringBuilder();
			msg.append(dadosArquivo.getVendedor().size());
			msg.append(" %n");
			gravarArq.printf(msg.toString());

			msg = new StringBuilder();
			msg.append(dadosArquivo.getVendaMaisCara());
			msg.append(" %n");
			gravarArq.printf(msg.toString());

			msg = new StringBuilder();
			msg.append(dadosArquivo.getPiorVendedor());
			msg.append(" %n");
			gravarArq.printf(msg.toString());

			arq.close();
		}

	}

	protected void lerArquivosDiretorio() {

		File file = new File("./in/");
		if (!file.exists()) {
			file.mkdir();
		}

		File[] arquivos = file.listFiles();

		List<DadosArquivo> dadosArquivos = new ArrayList<>();
		for (File arquivo : arquivos) {
			DadosArquivo dados = null;
			try {
				dados = lerArquivo(arquivo.getPath());
				dadosArquivos.add(dados);
			} catch (IOException | ExcecaoNegocio e) {
				System.out.println("Arquivo com layout invalido = " + arquivo.getPath());
				e.printStackTrace();
			}
		}

		try {
			gerarArquivos(dadosArquivos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected DadosArquivo lerArquivo(String path) throws IOException, ExcecaoNegocio {
		DadosArquivo dados = new DadosArquivo();

		String nomeArquivo = path.substring(path.lastIndexOf("\\"));
		dados.setNomeArquivo(nomeArquivo);

		String linha = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

			while ((linha = reader.readLine()) != null) {
				// para ignora linha em branco
				if (linha != null && !linha.trim().isEmpty()) {
					String[] dadosLinha = linha.split("ç");

					String codigo = dadosLinha[0];

					switch (codigo) {
					case codigoVendedor:
						dados.getVendedor().add(new DadosVendedor(dadosLinha));
						break;
					case codigoCliente:

						dados.getClientes().add(new DadosCliente(dadosLinha));
						break;
					case codigoVenda:

						dados.getVendas().add(new DadosVendas(dadosLinha));
						break;

					default:
						break;
					}
				}
			}

			// TODO: Validar dados id Venda deveria se unico.

			atualizarVendedores(dados);
		} finally {
			if (reader != null)
				reader.close();
		}

		return dados;
	}

	private void atualizarVendedores(DadosArquivo dados) {

		for (DadosVendas vendas : dados.getVendas()) {

			for (DadosVendedor vendedor : dados.getVendedor()) {
				if (vendedor.getNome().equals(vendas.getNomeVendedor())) {
					vendedor.setAcumuladorVendas(vendedor.getAcumuladorVendas() + vendas.getValorTotal());
				}
			}
		}
	}
}
