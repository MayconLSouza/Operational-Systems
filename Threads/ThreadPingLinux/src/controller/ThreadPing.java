package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//extends Thread
//Par�metros por construtor
//m�todo run()

public class ThreadPing extends Thread {
	
	private String serverName;

	public ThreadPing(String serverName) {
		this.serverName = serverName;
	}
	
	@Override
	public void run() {

		//verifica sistema operacional
		boolean flagLinux = isLinux();

		if (!flagLinux) {
			System.out.println("Linux nao identificado");
		} else {
			pingLinux();
		}

	}

	public boolean isLinux() {
		
		String os = System.getProperty("os.name");

		if(os.contains("Linux")) {
			return true;
		} else {
			return false;
		}
	}

	public void pingLinux(){
		try {
			Process process = Runtime.getRuntime().exec("ping -4 -c 10 " + serverName);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			int iteracao = 0;
			String[] server = serverName.split("\\.");
			while(linha != null) {				
				if (linha.contains("icmp")) {
					iteracao++;
					String[] time = linha.split("=");
					System.out.println(server[1] + " #" + iteracao + " tempo = " + time[3]);
				}
				if(linha.contains("avg")) {
					String[] media = linha.split("/");
					System.out.println("Media " + server[1] + " = " + media[4] + " ms");
				}
				linha = buffer.readLine(); // L� a pr�xima linha
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			String msgError = e.getMessage();
			System.err.println(msgError);
		}
	}
}