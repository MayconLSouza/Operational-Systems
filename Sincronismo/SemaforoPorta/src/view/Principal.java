/*
 * 4 pessoas caminham, cada uma em um corredor diferente. Os 4 corredores terminamem uma única porta. 
 * Apenas 1 pessoa pode cruzar a porta, por vez. Considere que cada corredor tem 200m e cada pessoa anda de 4 a 6 m/s. 
 * Cada pessoa leva de 1 a 2 segundos para abrir e cruzar a porta. Faça uma aplicação em java que simule essa situação.
 */

package view;

import java.util.concurrent.Semaphore;
import controller.PortaController;

public class Principal {

    public static void main(String[] args) {
        
        // Definição do número de permissões (apenas uma pessoa pode atravessar a porta de cada vez)
        int permissao = 1;
        // Criação de um objeto Semaphore chamado "semaforo" com uma permissão
        Semaphore semaforo = new Semaphore(permissao);
        
        // Laço for para criar e iniciar quatro threads representando as quatro pessoas
        for (int idPessoa = 1; idPessoa < 5; idPessoa++) {
            // Criação de uma nova instância da classe PortaController (que é uma thread) com o semáforo e o ID da pessoa
            Thread porta = new PortaController(semaforo, idPessoa);
            // Inicia a thread
            porta.start();
        }
    }
}

