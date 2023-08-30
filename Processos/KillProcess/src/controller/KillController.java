package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.edu.fatec.zl.info.operationalsystem.InfoOS;

public class KillController {

	public KillController() {
		super();
	}
	
	//Mostrar informações do sistema operacional
	public void os() {
		InfoOS os = new InfoOS();
		os.infoOS();
	}

	public void listaProcessos() {
		
		String os = System.getProperty("os.name");
		
		//Windows
		if (os.contains("Windows")) {
			String process = "TASKLIST /FO TABLE";
			processWindows(process);
		}
		//Linux
		else if (os.contains("Linux")) {
			String process = "ps -ef";
			processLinux(process);
		}
		else {
			System.out.println("Sistema operacional nao reconhecido");
		}
		
	}
	
	public void processWindows(String process) {
		try {
			Process processW = Runtime.getRuntime().exec(process);
			InputStream fluxo = processW.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
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
	
	public void processLinux(String process) {
		try {
			Process processL = Runtime.getRuntime().exec(process);
			InputStream fluxo = processL.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
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
	
	public void mataPid(String pid) {
		
		String os = System.getProperty("os.name");
		
		//Windows
		if (os.contains("Windows")) {
			String process = "TASKLIST /FO TABLE";
			try {
				Process processW = Runtime.getRuntime().exec(process);
				InputStream fluxo = processW.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if(linha.contains(pid)) {
						String processKill = "TASKKILL /PID " + pid;
						Runtime.getRuntime().exec(processKill);
						break;		//Sai do while após matar o processo
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
		//Linux
		else if (os.contains("Linux")) {
			String process = "ps -ef";
			try {
				Process processL = Runtime.getRuntime().exec(process);
				InputStream fluxo = processL.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if(linha.contains(pid)) {
						String processKill = "kill -9 " + pid;
						Runtime.getRuntime().exec(processKill);
						break;		//Sai do while após matar o processo
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
		else {
			System.out.println("Sistema operacional nao reconhecido");
		}
		
	}
	
	public void mataNome(String nameProcess) {
		
		String os = System.getProperty("os.name");
		
		//Windows
		if (os.contains("Windows")) {
			String process = "TASKLIST /FO TABLE";
			try {
				Process processW = Runtime.getRuntime().exec(process);
				InputStream fluxo = processW.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if(linha.contains(nameProcess)) {
						String processKill = "TASKKILL /IM " + nameProcess;
						Runtime.getRuntime().exec(processKill);
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
		//Linux
		else if (os.contains("Linux")) {
			String process = "ps -ef";
			try {
				Process processL = Runtime.getRuntime().exec(process);
				InputStream fluxo = processL.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if(linha.contains(nameProcess)) {
						String processKill = "pkill -f " + nameProcess;
						Runtime.getRuntime().exec(processKill);						
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
		else {
			System.out.println("Sistema operacional nao reconhecido");
		}
	}
	
}
