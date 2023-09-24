/*Um servidor com multiprocessamento recebe requisições que envolve realizar cálculos e fazer transações com bancos de dados. 
 * Por ter uma quantidade grande de núcleos de processamentos e threads, além de um bom algoritmo de escalonamento de threads, 
 * enquanto as threads fazem cálculos, estes podem ocorrer simultaneamente, mas quando se faz a transação no banco de dados, 
 * esta deve ser apenas uma thread por vez. Considere um conjunto de threads com IDs definidas na própria aplicação com números iniciando em 1 
 * e incrementando de um em um. As threads tem comportamento como segue:
	a) Threads com ID dividido por 3 resultando em resto igual a um fazem as transações:
		- Cálculos de 0,2 a 1,0 segundos
		- Transação de BD por 1 segundo
		- Cálculos de 0,2 a 1,0 segundos
		- Transação de BD por 1 segundo
	b) Threads com ID dividido por 3 resultando em resto igual a dois fazem as transações:
		- Cálculos de 0,5 a 1,5 segundos
		- Transação de BD por 1,5 segundo
		- Cálculos de 0,5 a 1,5 segundos
		- Transação de BD por 1,5 segundo
		- Cálculos de 0,5 a 1,5 segundos
		- Transação de BD por 1,5 segundo
	c) Threads com ID dividido por 3 resultando em resto igual a zero fazem as transações:
		- Cálculos de 1 a 2 segundos
		- Transação de BD por 1,5 segundo
		- Cálculos de 1 a 2 segundos
		- Transação de BD por 1,5 segundo
		- Cálculos de 1 a 2 segundos
		- Transação de BD por 1,5 segundo
	Faça uma aplicação em Java que simule a situação de 21 Threads simultâneas, com exibição em console de cada passo que a Thread está realizando.
*/
package view;

import controller.ServidorController;

public class Principal {

	public static void main(String[] args) {
		
		Thread[] requisicao = new Thread[21];	// Array para armazenar as 21 requisicoes
		
		// Gerar Requisicao
		for(int threadID = 0 ; threadID < 21 ; threadID++ ) {
			requisicao[threadID] = new ServidorController(threadID);
			// Inicia requisicao
			requisicao[threadID].start();
		} 
		
		// Aguarde a conclusao de todas as requisicoes
        for (Thread thread : requisicao) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                String msgError = e.getMessage();
                System.err.println(msgError);
            }
        }
        
        // Informa ao usuario a conclusao de todas requisicoes
        System.out.println("\nTodas as requisicoes foram concluidas.");

	}

}
