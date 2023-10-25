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

public class ArquivosDiretoriosController implements
	IArquivosDiretorios{

	@Override
	public void leDiretorio(String path) throws IOException {
		File dir = new File(path);
		if(dir.exists() && dir.isDirectory()) {
			File[] lista = dir.listFiles();
			for(File f : lista) {
				if(f.isDirectory()) {
					System.out.println("[" + f.getName()+"]");
				}
			}
			for(File f : lista) {
				if(f.isFile()) {
					System.out.println(f.getName());
				}
			}
		} else {
			throw new IOException("Diretorio Invalido");
		}
	}

	@Override
	public void criaTxt(String path, String nome) throws IOException {
		File dir = new File(path);
		File arquivo = new File(path, nome + ".txt");
		if(dir.exists()) {
			boolean flagArquivoExiste = false;
			if(arquivo.exists()) {
				flagArquivoExiste = true;
			}
			String conteudo = geraConteudoTxt();
			FileWriter fw = 
					new FileWriter(arquivo, flagArquivoExiste);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(conteudo);
			pw.flush();;
			pw.close();
			fw.close();
		} else {
			throw new IOException("Diretorio Invalido");
		}
	}
	
	private String geraConteudoTxt() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while(!linha.equals("fim")) {
			linha = JOptionPane.showInputDialog(null, 
					"Digite uma frase", "Entrada", 
					JOptionPane.INFORMATION_MESSAGE);
			buffer.append(linha + "\r\n");
		}
		return buffer.toString();
	}

	@Override
	public void leTxt(String absolutePath) throws IOException {
		File arquivo = new File(absolutePath);
		if(arquivo.exists()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}

	@Override
	public void abreArquivoApp(String absolutePath) throws IOException {
		File arquivo = new File(absolutePath);
		if(arquivo.exists()) {
			Desktop desk = Desktop.getDesktop();
			desk.open(arquivo);
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}

	@Override
	public void propriedades(String absolutePath) throws IOException {
		File arquivo = new File(absolutePath);
		if(arquivo.exists()) {
			if(arquivo.canRead()) {
				System.out.println("Arquivo permite leitura");
			} else {
				System.out.println("Arquivo nao permite leitura");
			}
			if(arquivo.canWrite()) {
				System.out.println("Arquivo permite escrita");
			} else {
				System.out.println("Arquivo nao permite escrita");
			}
			System.out.println(arquivo.getTotalSpace());
			if(arquivo.isHidden()) {
				System.out.println("Arquivo oculto");
			} else {
				System.out.println("Arquivo visivel");
			}
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}

}
