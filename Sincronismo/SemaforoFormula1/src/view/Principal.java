/* Você foi contratado para automatizar um treino de Formula 1. As regras estabelecidas pela direcao da provas sao simples:
	“No maximo 5 carros das 7 escuderias (Cada escuderia tem 2 carros diferentes, portanto, 14 carros no total) presentes
	 podem entrar na pista simultaneamente, mas apenas um carro de cada equipe. O segundo carro deve ficar a espera, caso um
	 companheiro de equipe ja esteja na pista. Cada piloto deve dar 3 voltas na pista. O tempo de cada volta devera ser
	 exibido e a volta mais rapida de cada piloto deve ser armazenada para, ao final, exibir o grid de largada, ordenado
     do menor tempo para o maior.”
*/

package view;

import java.util.concurrent.Semaphore;
import controller.ThreadQualificacao;

public class Principal {

	public static void main(String args[]) {

		int carros = 14;	// Numero total de carros
		double[] voltaRapida = new double[carros];	// Array para armazenar as voltas mais rapidas de cada carro
		Semaphore[] scuderia = new Semaphore[7];	// Semaforos para controlar a entrada dos carros da mesma escuderia
		Thread[] carroThread  = new ThreadQualificacao[carros];	// Array para armazenar as threads dos carros		
		int[] indices = new int[14];

		// Inicializacao dos semaforos e das threads dos carros
		for(int i = 0; i < 7; i++) {

			scuderia[i] = new Semaphore(1);	// Cada escuderia pode ter apenas um carro na pista
		
			for(int j = 0; j < 2; j++) {
				
				int idCarro = (i * 2) + j;	// Calculo do ID do carro com base na escuderia e no indice dentro da escuderia
				carroThread[idCarro] = new ThreadQualificacao(idCarro, scuderia[i], voltaRapida);
				carroThread[idCarro].start();	// Inicio da thread do carro
			
			}

		}

		// Aguarda o termino da qualificacao
		for (Thread qualificacao : carroThread) {

            try {
                qualificacao.join();
            } catch (Exception e) {
                String msgError = e.getMessage();
                System.err.println(msgError);
            }

          }

		// Inicializacao do array de indices
    	for (int i = 0; i < carros; i++) {
        	indices[i] = i;	
    	}

		// Ordenacao dos carros pelo tempo da volta mais rapida
		for (int i = 0; i < carros - 1; i++) {
        		for (int j = 0; j < carros - i - 1; j++) {
            		if (voltaRapida[indices[j]] > voltaRapida[indices[j + 1]]) {
                		// Troque os indices se a volta atual for mais lenta
                		int aux = indices[j];
                		indices[j] = indices[j + 1];
                		indices[j + 1] = aux;
            		}
        		}
    		}

		// Exiba o grid de largada ordenado
    	System.out.println("\nGrid de Largada:\n");
		for (int i = 0; i < carros; i++) {
        		int idCarro = indices[i];
        		System.out.println("#" + ( i + 1) + " --- Carro #" + idCarro + " --> Tempo da melhor volta: " + voltaRapida[idCarro] + " s");
    	}
	
	}

}
