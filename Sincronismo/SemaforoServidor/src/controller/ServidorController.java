package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class ServidorController extends Thread {
	
	private int threadID;		// Identificacao de requisicao
	private int resto;	// Variavel que ira definir quais operacoes devem ser realizadas
	private double tempoCalculo;	// Tempo aleatorio de processamento da operacao de calculo
	private double tempoDataBase = 1.5;	// Tempo de processamento da operacao de banco de dados
	private Semaphore dataBase = new Semaphore(1);	// Semaforo que libera apenas 1 requisicao por vez ter acesso ao banco de dados
	private DecimalFormat decimalFormat = new DecimalFormat("#0.000");	// Instancia para formatar tempo gerado em 3 casas decimais
	private String tempoFormatado;	// Tempo para exibicao em console
	
	
	public ServidorController(int threadID) {
		this.threadID = threadID;
	}
	
	@Override
	public void run() {
		
		// Calcula resto para definir quais operacoes realizar
		resto = threadID % 3;
		
		switch(resto) {
		
			case 0:
				// Realiza operacoes de calculo em um intervalo de 1 a 2 s
				calculo0();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				// Realiza operacoes de calculo em um intervalo de 1 a 2 s
				calculo0();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				// Realiza operacoes de calculo em um intervalo de 1 a 2 s
				calculo0();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				break;
			case 1:
				// Realiza operacoes de calculo em um intervalo de 0.2 a 1 s
				calculo1();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				// Realiza operacoes de calculo em um intervalo de 0.2 a 1 s
				calculo1();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				break;
			case 2:
				// Realiza operacoes de calculo em um intervalo de 0.5 a 1.5 s
				calculo2();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				// Realiza operacoes de calculo em um intervalo de 0.5 a 1.5 s
				calculo2();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				// Realiza operacoes de calculo em um intervalo de 0.5 a 1.5 s
				calculo2();
				// Realiza operacao no banco de dados em 1.5 s
				bd();
				break;
			default:
				System.out.println("Nao foi possivel realizar requisicao #" + threadID);
				
		}
		
	}

	private void bd() {
		
		// Realiza operacao de banco de dados
		try {
			// Solicita semaforo para realizar operacao em banco de dados
			dataBase.acquire();
			// Aguarda finalizar a operacao
			Thread.sleep((long) tempoDataBase);
			System.out.println("Thread #" + (threadID + 1) + " realizou operacao de banco de dados");
		} catch (InterruptedException e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
		} finally {
			// Libera semaforo de operacao em banco de dados
			dataBase.release();	
		}
		
	}

	private void calculo0() {
		
		// Realiza operacao de calculo
		try {
			// Gera tempo aleatorio entre 1 e 2 segundos
			tempoCalculo = 1 + Math.random();
			// Aguarda finalizar a operacao
			Thread.sleep((long) tempoCalculo);
			// Formata o tempo gerado para uma facil leitura em console
			tempoFormatado = decimalFormat.format(tempoCalculo);
			System.out.println("Thread #" + (threadID + 1) + " realizou operacoes de calculo em " + tempoFormatado + " s");
		} catch (InterruptedException e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
		}
		
	}
	
	private void calculo1() {
		
		// Realiza operacao de calculo
		try {
			// Gera tempo aleatorio entre 0.2 e 1 s
			tempoCalculo = Math.random() * 0.8 + 0.2;
			// Aguarda finalizar a operacao
			Thread.sleep((long) tempoCalculo);
			// Formata o tempo gerado para uma facil leitura em console
			tempoFormatado = decimalFormat.format(tempoCalculo);
			System.out.println("Thread #" + (threadID + 1) + " realizou operacoes de calculo em " + tempoFormatado + " s");
		} catch (InterruptedException e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
		}		
		
	}
	
	private void calculo2() {
		
		// Realiza operacao de calculo
		try {
			// Gera tempo aleatorio entre 0.5 e 1.5 s
			tempoCalculo = Math.random() + 0.5;
			// Aguarda finalizar a operacao
			Thread.sleep((long) tempoCalculo);
			// Formata o tempo gerado para uma facil leitura em console
			tempoFormatado = decimalFormat.format(tempoCalculo);
			System.out.println("Thread #" + (threadID + 1) + " realizou operacoes de calculo em " + tempoFormatado + " s");
		} catch (InterruptedException e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
		}		
		
	}

}
