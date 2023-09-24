/*
 * Existem diversos jogos de simulação. Um deles simula a participação de cozinheiros em uma cozinha profissional realizando pratos. 
 * Numa das fases, o cozinheiro precisa realizar o cozimento de 5 pratos simultâneos, onde cada cozimento não depende da interação do jogador. 
 * Pratos de ID ímpar, são chamados de Sopa de Cebola e levam de 0,5 a 0,8 segundos para ficar prontos. 
 * Pratos de ID par, são chamados de Lasanha a Bolonhesa e levam de 0,6 a 1,2 segundos para ficar prontos. 
 * Quando um prato inicia, é necessário comunicar, em console, que se iniciou e, a cada 0,1 segundos, 
 * deve-se exibir o percentual de cozimento (O percentual é definido pelo tempo total dividido por 0,1 segundos). 
 * Quando um prato fica pronto, é necessário comunicar em console o final e fazer a entrega, que leva 0,5 segundos. 
 * O jogador só pode entregar um prato por vez e deve comunicar a entrega. Simular a situação em Java.
 */
package view;

import controller.OvercookedController;

public class Principal {

	public static void main(String[] args) {
		
		Thread[] prato = new Thread[5];
		
		// Gera pratos a serem entregues
		for(int pratoID = 0; pratoID < 5 ; pratoID++ ) {
			prato[pratoID] = new OvercookedController(pratoID);
			prato[pratoID].start();
		}
		
		// Aguarde a conclusao de todos os pratos
        for (Thread thread : prato) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                String msgError = e.getMessage();
                System.err.println(msgError);
            }
        }
        
        // Informa ao usuario a conclusao de todos os pratos
        System.out.println("\nPARABENS!!! TODOS OS PRATOS FORAM ENTREGUES. FASE CONCLUIDA.");

	}

}
