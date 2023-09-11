package controller;

import br.edu.fateczl.tempo.Tempo;

//extends Thread
//Parâmetros por construtor
//método run()

public class ThreadVetor extends Thread{
	
	private int number;
	private int[] array;
	private double tempoInicial;
	
	//constructor
	public ThreadVetor(int number, int[] array) {
		this.number = number;
		this.array = array;
	}
	
	@Override
	public void run() {
		Tempo tempo = new Tempo();
		//par
		if( (number % 2) == 0) {
			tempoInicial = System.nanoTime();
			for (int i = 0; i < array.length; i++) {
				//percorre o vetor
			}
			double tempoFinalS = tempo.gerarTempoS(tempoInicial);
			System.out.println("Thread " + number + "(for): " + String.format("%.7f", tempoFinalS) + " s");
		}
		
		//ímpar
		else if( (number % 2) != 0) {
			tempoInicial = System.nanoTime();
			for (int i : array ) {
				//percorre o vetor
			}
			double tempoFinal = tempo.gerarTempoS(tempoInicial);
			System.out.println("Thread " + number + "(for each): " + String.format("%.7f", tempoFinal) + " s");
		}
	}

}
