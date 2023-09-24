package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class ThreadQualificacao extends Thread {

	private int idCarro;	// Identificacao do carro
	private Semaphore pista = new Semaphore(5); // Semaforo para controlar o acesso a pista (maximo de 5 carros)
    private Semaphore scuderia; // Semaforo para controlar a entrada de escuderias (maximo de 1 carro por escuderia)
    private double melhorVolta = 1.31; // Inicializa a melhor volta com um valor alto
    private double ultimaVolta; // Variavel para armazenar o tempo da ultima volta
    private double[] voltaRapida; // Array compartilhado para armazenar as voltas mais rapidas de cada carro
    Random random = new Random(); // Gerador de numeros aleatorios para simular os tempos de volta

	public ThreadQualificacao (int idCarro, Semaphore scuderia, double[] voltaRapida) {
		this.idCarro = idCarro;
		this.scuderia = scuderia;
		this.voltaRapida = voltaRapida;
	}

	public void run() {

		try {
			scuderia.acquire(); // Aguarda a autorizacao da escuderia (apenas um carro por equipe)
            pista.acquire(); // Aguarda a autorizacao para entrar na pista (maximo de 5 carros)
            carroPista(); // Simula a passagem do carro pela pista
		} catch (Exception e) {
			String msgError = e.getMessage();
			System.err.println(msgError);
		} finally {
			scuderia.release(); // Libera a autorizacao da escuderia
            pista.release(); // Libera a autorizacao para sair da pista
		}
	
	}

	private void carroPista() {

		// Simula 3 voltas na pista
		for(int i = 0; i < 3; i++) {

			 // Gera um tempo de volta aleatorio entre 1.05 e 1.30 segundos
			ultimaVolta = Math.round((1.05 + (random.nextDouble() * (1.30 - 1.05))) * 1000.0) / 1000.0;
			System.out.println("Tempo da volta " + (i + 1) + " do carro # " + idCarro + " = " + ultimaVolta + " s ");
			// Atualiza a melhor volta se a volta atual for mais rapida
			if( ultimaVolta < melhorVolta ) {
				melhorVolta = ultimaVolta;
			}

		}

		// Armazena a melhor volta no array compartilhado
		voltaRapida[idCarro] = melhorVolta;
	
	}

}

