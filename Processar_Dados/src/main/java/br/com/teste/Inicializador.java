package br.com.teste;

import java.nio.file.FileSystems;

public class Inicializador {

	public static void main(String[] args) {
		System.out.println("Ler arquivos existentes");
		new Processar().lerArquivosDiretorio();
		System.out.println("Arquivos existentes processados");

		System.out.println("Verificar Novos arquivos");
		new VerificarAlteracao().watchDirectoryPath(FileSystems.getDefault().getPath(".\\in"));
	}

}
