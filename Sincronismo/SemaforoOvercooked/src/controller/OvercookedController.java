package controller;

import java.util.concurrent.Semaphore;

public class OvercookedController extends Thread {
	
	private int pratoID;	// Identificacao de prato
	private double tempoCozimentoTotal;		// Tempo total de cozimento de um prato gerado aleatoriamente
	private double tempoCozimentoAtual = 0;		// Tempo atual de cozimento de um prato
	private int percentualCozimento;		// Percentual de cozimento de um prato 
	private Semaphore entrega = new Semaphore(1);	// Semaforo que garante a entrega de apenas 1 prato por vez

	public OvercookedController(int pratoID) {
		this.pratoID = pratoID + 1;
	}

	@Override
	public void run() {
		
		// Lasanha
		if(pratoID % 2 == 0) {
			
			// Informa usuario que iniciou cozimento do prato
			System.out.println("Lasanha #" + pratoID + " comecou a ser cozinhada");
			// Gera tempo de cozimento total entre 0.5 e 0.8 s
			tempoCozimentoTotal = Math.random() * 0.3 + 0.5;
			// Cozinha o prato
			cozinharPrato(tempoCozimentoTotal);
			// Informa usuario que o prato ficou pronto
			System.out.println("Lasanha #" + pratoID + " ficou pronta");
			// Entrega o prato
			entregarPrato();
			// Informa usuario que prato foi entregue
			System.out.println("Lasanha #" + pratoID + " foi entregue");
		
		// Sopa de Cebola
		} else {
			
			// Informa usuario que iniciou cozimento do prato
			System.out.println("Sopa de Cebola #" + pratoID + " comecou a ser cozinhada");
			// Gera tempo de cozimento total entre 0.6 e 1.2 s
			tempoCozimentoTotal = Math.random() * 0.6 + 0.6;
			// Cozinha o prato
			cozinharPrato(tempoCozimentoTotal);
			// Informa usuario que o prato ficou pronto
			System.out.println("Sopa de Cebola #" + pratoID + " ficou pronta");
			// Entrega o prato
			entregarPrato();
			// Informa usuario que prato foi entregue
			System.out.println("Sopa de Cebola #" + pratoID + " foi entregue");
			
		}
		
	}

	private void cozinharPrato(double tempoCozimentoTotal) {
		
		// Cozinha o prato de acordo com o tempo de cozimento total
		while(tempoCozimentoAtual < tempoCozimentoTotal) {
			
			try {
				// Aguarda cozimento de 0.1 s
				Thread.sleep(100);
				tempoCozimentoAtual += 0.1;
				// Calcula percentual de cozimento 
				percentualCozimento = (int) ( (tempoCozimentoAtual / tempoCozimentoTotal) * 100);
				// Nao deixa queimar o prato
				if(percentualCozimento >= 100) {
					break;
				}
				// Informa ao usuario qual o percentual de cozimento do prato atual
				System.out.println("Lasanha #" + pratoID + " ja cozinhou " + percentualCozimento + "%");
			} catch (InterruptedException e) {
				String msgErro = e.getMessage();
				System.err.println(msgErro);
			}
			
		}
		
	}
	
	private void entregarPrato() {
		
		try {
			// Solicita semaforo para realizar entrega do prato
			entrega.acquire();
			// Realiza a entrega do prato
			Thread.sleep(500);
		} catch (InterruptedException e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
		} finally {
			// Libera semaforo de entrega de prato
			entrega.release();
		}
		
	}

}
