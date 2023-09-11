package controller;

//extends Thread
//Parâmetros por construtor
//método run()

public class ThreadSoma extends Thread {
	
	private int idThread;
	private int[] linha;
	
	public ThreadSoma(int idThread, int[] linha) {
		this.idThread = idThread;
		this.linha = linha;
	}
	
	@Override
	public void run() {
		int soma = 0;
		for(int index = 0 ; index < 5 ; index++ ) {
			soma += linha[index];
		}
		System.out.println("Soma da linha[" + idThread + "] = " + soma);
	}

}
