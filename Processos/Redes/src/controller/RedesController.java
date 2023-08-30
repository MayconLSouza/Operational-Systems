package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.edu.fatec.zl.info.operationalsystem.InfoOS;

public class RedesController {

	public RedesController() {
		super();
	}
	
	//Mostrar informações do sistema operacional
	public void os() {
		InfoOS os = new InfoOS();
		os.infoOS();
	}
	
	//Mostrar configurações de IP
	public void ip() {
		
		//Verifica o sistema operacional
		String os = System.getProperty("os.name");
		
		//Windows
		if(os.contains("Windows")) {
			String processIP = "ipconfig";
			ipWindows(processIP);
		}
		//Linux
		else if (os.contains("Linux")) {
			String processIP = "ip addr";
			ipLinux(processIP);
		}
		else {
			System.out.println("Sistema Operacional nao reconhecido");
		}
	}
	
	// IP Windows
	public void ipWindows(String processIP) {
		String[] adaptador = null;
		try {
			Process process = Runtime.getRuntime().exec(processIP);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains("Adaptador")) {
					adaptador = linha.split(":"); 		// Pega as 3 primeiras palavras da linha
				}
				if(linha.contains("IPv4")) {
					String[] ipv4 = linha.split(": ");				// Pega o número IPv4
					System.out.println("Nome do Adaptador: " + adaptador[0]);
					System.out.println("IPv4: " + ipv4[1]);
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
	}
	
	// IP Linux
	public void ipLinux(String processIP) {
		String[] adaptador = null;
		try {
			Process process = Runtime.getRuntime().exec(processIP);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains("mtu")) {
					adaptador = linha.split(": ");					// Pega o nome do adaptador
				}
				if(linha.contains("inet ")) {
					String[] inet = linha.split(" ");				// Pega o número IPv4
					System.out.println("Nome do Adaptador: " + adaptador[1]);
					System.out.println("IPv4: " + inet[5]);
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
	}
	
	//Realiza a chamada de ping
	public void ping() {
		
		//Verifica o sistema operacional
		String os = System.getProperty("os.name");
		
		//Windows
		if(os.contains("Windows")) {
			String processPing = "ping -4 -n 10 www.google.com.br";
			pingWindows(processPing);
		}
		//Linux
		else if (os.contains("Linux")) {
			String processPing = "ping -4 -c 10 www.google.com.br";
			pingLinux(processPing);
		}
		else {
			System.out.println("Sistema Operacional nao reconhecido");
		}
	}
	
	public void pingWindows(String processPing) {
		try {
			Process process = Runtime.getRuntime().exec(processPing);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				//Média
				if(linha.contains("dia")) {
					String[] media = linha.split("= ");
					System.out.println("Media = " + media[3]);
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
	}
	
	public void pingLinux(String processPing) {
		try {
			Process process = Runtime.getRuntime().exec(processPing);
			InputStream fluxo = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				//Média
				if(linha.contains("avg")) {
					String[] media = linha.split("/");
					System.out.println("Media = " + media[4] +" ms");
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
	}
}
