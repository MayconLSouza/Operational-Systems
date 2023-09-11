package view;

import controller.FrogRace;

public class Principal {

	public static void main(String[] args) {
		
		// Loop para criar e iniciar Threads para cada sapo
		for(int sapo = 1; sapo < 6 ; sapo++ ) {
			// Cria uma nova Thread, passando o número de identificação do sapo
			Thread thread = new FrogRace(sapo);
			// Inicia a Thread
			thread.start();
		}

	}

}
