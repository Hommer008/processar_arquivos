package br.com.teste;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collections;

import br.com.teste.dto.DadosArquivo;
import br.com.teste.excecao.ExcecaoNegocio;

public class VerificarAlteracao {

	public void watchDirectoryPath(Path path) {
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + path + " is not a folder");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("Watching path: " + path);

		FileSystem fs = path.getFileSystem();

		try (WatchService service = fs.newWatchService()) {

			path.register(service, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

			WatchKey key = null;
			while (true) {
				key = service.take();

				Kind<?> kind = null;
				for (WatchEvent<?> watchEvent : key.pollEvents()) {

					kind = watchEvent.kind();
					if (OVERFLOW == kind) {
						continue; // loop
					} else if (ENTRY_CREATE == kind) {

						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println("Novo arquivo: " + newPath);

						// Sleep para evitar concorrência na leitura do arquivo.
						Thread.sleep(100);
						try {
							Processar processar = new Processar();
							DadosArquivo dados = processar.lerArquivo(".\\in\\" + newPath.toString());
							processar.gerarArquivos(Collections.singletonList(dados));
						} catch (ExcecaoNegocio en) {
							en.printStackTrace();
						}

					} else if (ENTRY_MODIFY == kind) {

						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println("Arquivo modificado: " + newPath);

					} else if (ENTRY_DELETE == kind) {
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println("Arquivo deletado: " + newPath);

					}
				}

				if (!key.reset()) {
					break; // loop
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}

}