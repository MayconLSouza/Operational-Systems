package controller;

public class OperacoesController {

	public OperacoesController() {
		super();
    }

    // Gerar tempo gasto para percorrer o vetor
    public void gerarTempo(int[] vetor, int tamanho) {
        double tempoInicial = System.nanoTime();
        for (int i = 0; i < tamanho; i++) {
        	//Correndo o vetor
        }
        double tempoFinal = System.nanoTime();
        // Calcula tempo total de nano para segundos
        double tempoTotal = (tempoFinal - tempoInicial) / Math.pow(10, 9);
        System.out.println("Tempo em segundos para vetor[" + tamanho + "] ==> " + String.format("%.7f", tempoTotal) + " s");
    }
}