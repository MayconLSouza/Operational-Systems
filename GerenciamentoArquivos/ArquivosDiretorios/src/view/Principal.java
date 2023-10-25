package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosDiretoriosController;
import controller.IArquivosDiretorios;

public class Principal {

	public static void main(String[] args) {
		
		//leDiretorio
//		String path = "C:\\Windows";
//		IArquivosDiretorios ad =
//				new ArquivosDiretoriosController();
//		try {
//			ad.leDiretorio(path);
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, 
//					e.getMessage(), "ERRO", 
//					JOptionPane.ERROR_MESSAGE);
//		}
		
		//criaTxt
//		String path = "C:\\TEMP";
//		String nome = "teste";
//		IArquivosDiretorios ad =
//				new ArquivosDiretoriosController();
//		try {
//			ad.criaTxt(path, nome);
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, 
//					e.getMessage(), "ERRO", 
//					JOptionPane.ERROR_MESSAGE);
//		}
		
		//leTxt
//		String absolutePath = "C:\\TEMP\\teste.txt";
//		IArquivosDiretorios ad = new ArquivosDiretoriosController();
//		try {
//			ad.leTxt(absolutePath);;
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, 
//					e.getMessage(), "ERRO", 
//					JOptionPane.ERROR_MESSAGE);
//		}
		
		//abreArquivoApp
//		String absolutePath = "C:\\TEMP\\teste.txt";
//		IArquivosDiretorios ad = new ArquivosDiretoriosController();
//		try {
//			ad.abreArquivoApp(absolutePath);
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, 
//					e.getMessage(), "ERRO", 
//					JOptionPane.ERROR_MESSAGE);
//		}
		
		//propriedades
		String absolutePath = "C:\\TEMP\\teste.txt";
		IArquivosDiretorios ad = new ArquivosDiretoriosController();
		try {
			ad.propriedades(absolutePath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
					e.getMessage(), "ERRO", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
