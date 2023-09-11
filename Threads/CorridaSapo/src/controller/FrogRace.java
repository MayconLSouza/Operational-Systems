package controller;

import java.util.concurrent.atomic.AtomicInteger;

//extends Thread
//Parâmetros por construtor
//método run()

public class FrogRace extends Thread{
	
	private int sapoId;														//Identificação do sapo
	private int tamanhoSalto;												//Tamanho do salto que o sapo pode dar entre 0 e 10 m
	private int distanciaCorrida = 100;										//Distância a ser percorrida de 100 m
	private int[] distanciaPercorrida = {0, 0, 0, 0, 0}; 					//Distância que o sapo já percorreu
	private int[] salto = {0, 0, 0, 0, 0}; 									//Quantidade de saltos que ele já deu
	private static AtomicInteger classificacao = new AtomicInteger(1);		//Classificacao final da corrida
	private int posicao;													//Posicao de chegada do sapo

	//constructor
	public FrogRace(int sapoId) {
		this.sapoId = sapoId;
	}
	
	@Override
	public void run() {
		
		while(distanciaPercorrida[sapoId-1] < distanciaCorrida) {
			
			// Gera um tamanho de salto aleatório de 0 a 10 metros
			tamanhoSalto = (int)(Math.random()*11);
			
			// Atualiza a distância percorrida e o número de saltos do sapo
			distanciaPercorrida[sapoId-1] += tamanhoSalto;
			salto[sapoId-1]++;
			
			// Exibe informações sobre o salto do sapo no console
			System.out.println("\nSapo " + sapoId + "\nSalto: " + salto[sapoId-1] + "\nTamanho do salto: " + tamanhoSalto + " m\nDistancia Percorrida: " + distanciaPercorrida[sapoId-1] + " m\n");
			
			try {
				// Pausa a Thread por 100 milissegundos
				sleep(100);
			}catch (Exception e) {
				String MsgError = e.getMessage();
				System.out.println(MsgError);
			}
			
		}
		
		// Determina a posição do sapo usando uma variável atômica
		posicao = classificacao.getAndIncrement();
		
		try {
			// Pausa a Thread por 5 segundos para os outros sapos terminarem a corrida
			sleep(5000);
			//Exibe posição final
			System.out.println("\nSapo #" + sapoId + ": " + posicao + "° lugar");
		} catch (Exception e) {
			String MsgError = e.getMessage();
			System.out.println(MsgError);
		}
		
	}

}
