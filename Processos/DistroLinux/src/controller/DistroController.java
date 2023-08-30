package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {

	public DistroController() {
		super();
	}
	
	public void exibeDistro() {
		
		String os = System.getProperty("os.name");

		if(os.contains("Linux")) {
			String process = "cat /etc/os-release";
			try {
				Process processW = Runtime.getRuntime().exec(process);
				InputStream fluxo = processW.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if(linha.contains("PRETTY_NAME")) {
						String[] name = linha.split("=");
						System.out.println("NAME = " + name[1]);
					}
					if(linha.contains("VERSION_ID")) {
						String[] version = linha.split("=");
						System.out.println("VERSION = " + version[1]);
					}
					linha = buffer.readLine(); // Lê a próxima linha
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				String msgError = e.getMessage();
				System.err.println(msgError);
			}
		} else {
			System.out.println("Sistema operacional nao reconhecido como Linux");
		}
	}

}
