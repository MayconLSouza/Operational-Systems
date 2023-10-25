package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{

	public ArquivosController() {
		super();
	}

	@Override
	public void readDir(String path) throws IOException {
		//Define caminho
		File dir = new File(path);
		//Verifico se diretorio existe
		if(dir.exists() && dir.isDirectory()) {
			//Listo os arquivos do diretorio
			File[] files = dir.listFiles();
			for(File f : files) {
				//Verifico se e arquivo
				if(dir.isFile()) {
					System.out.println("     \t" + f.getName());
				} else {
					System.out.println("<DIR>\t" + f.getName());
				}
			}
		} else {
			throw new IOException("Diretorio Invalido");
		}
	}

	@Override
	public void createFile(String path, String name) throws IOException {
		//Define caminho
		File dir = new File(path);
		//Define arquivo
		File arq = new File(path, name);
		//Verifico se diretorio existe
		if(dir.exists() && dir.isDirectory()) {
			//Verifico se arquivo existe
			boolean flagExiste = false;
			if(arq.exists()) {
				flagExiste = true;
			}
			//Gera um texto
			String conteudo = geraTxt();
			//abre o arquivo e define operacao(write ou append)
			FileWriter fileWriter = new FileWriter(arq, flagExiste);
			//escreve o conteudo no arquivo
			PrintWriter print = new PrintWriter(fileWriter);
			//abre o arquivo e escreve o conteudo
			print.write(conteudo);
			//finaliza a escrita
			print.flush();
			//fecha o arquivo
			print.close();
			//fecha o arquivo
			fileWriter.close();
		} else {
			throw new IOException("Diretorio Invalido");
		}
	}

	private String geraTxt() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while(!linha.equalsIgnoreCase("fim")) {
			linha = JOptionPane.showInputDialog(null, "Digite uma frase",
						"Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
			if(!linha.equalsIgnoreCase("fim")) {
				buffer.append(linha + "\r\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public void readFile(String path, String name) throws IOException {
		//Define arquivo
		File arq = new File(path, name);
		//Verifico se o arquivo existe
		if(arq.exists() && arq.isFile()) {
			//abre arquivo
			FileInputStream fluxo = new FileInputStream(arq);
			//le arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo);
			//converte arquivo
			BufferedReader buffer = new BufferedReader(leitor);
			//coloca conteudo do arquivo no buffer
			String linha = buffer.readLine();
			//realiza a operacao
			while(linha != null) {	//procurando EOF(end of file)
				System.out.println(linha);
				linha = buffer.readLine();
			}
			//fecha arquivo
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}

	@Override
	public void openFile(String path, String name) throws IOException {
		//Define arquivo
		File arq = new File(path, name);
		//Verifica se o arquivo existe
		if(arq.exists() && arq.isFile()) {
			Desktop desktop = Desktop.getDesktop();
			//abre arquivo
			desktop.open(arq);
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}

}
