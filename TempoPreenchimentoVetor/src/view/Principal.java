package view;

import controller.OperacoesController;

public class Principal {

	public static void main(String[] args) {
		int[] vetor = new int[1000];
        int tamanho = vetor.length;
		
		//Preencher o vetor com todas as posições com valor 0
        for(int i = 0; i < tamanho; i++) {
        	vetor[i] = 0;
        }
        
		OperacoesController op = new OperacoesController();
		op.gerarTempo(vetor, tamanho);
		
	}

}
