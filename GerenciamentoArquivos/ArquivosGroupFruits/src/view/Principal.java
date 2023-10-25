package view;

import java.io.IOException;

import controller.GroupFruitsController;
import controller.IGroupFruitsController;

public class Principal {

	public static void main(String[] args) {
		
		IGroupFruitsController gpFruits = new GroupFruitsController();
		String path = "C:\\Users\\Mayco\\Documentos\\Fatec\\ZL\\3-SEM\\Sistema operacional I\\Aula09_SistemaArquivos";
		String name = "generic_food.csv";
		
		try {
			gpFruits.readFile(path, name);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
