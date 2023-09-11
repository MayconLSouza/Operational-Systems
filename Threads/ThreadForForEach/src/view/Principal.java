package view;

import controller.ThreadVetor;

public class Principal {

	public static void main(String[] args) {
		
		int[] array = new int[1000];
		
		//Preencher vetor
		for (int i = 0; i < 1000; i++) {
			array[i] = (int)(Math.random()*100+1);
		}
		
		// Iniciar Thread
		for(int i = 0; i < 2 ; i++) {
			Thread threadVetor = new ThreadVetor(i + 1, array);
			threadVetor.start();
		}
	}

}
