/*
 * Fazer uma aplicação que gerencie um cruzamento de 2 vias, onde temos 4 carros e cada um irá para um sentido (norte, sul, leste, oeste).
 * Para tal, usar uma variável sentido, que será alterado pela Thread que controla cada carro com a movimentação do carro.
 * Quando a Thread tiver a possibilidade de ser executada, ela deve imprimir em console o sentido que o carro está passando.
 * Só pode passar um carro por vez no cruzamento.
 */
package view;

import java.util.concurrent.Semaphore;

import controller.CruzamentoController;

public class Principal {

    public static void main(String[] args) {
        
        // Inicializa um semáforo com uma permissão (apenas um carro pode passar por vez)
        int permissao = 1;
        Semaphore semaforo = new Semaphore(permissao);
        
        // Cria quatro threads para representar os carros no cruzamento
        for (int idCarro = 0; idCarro < 4; idCarro++) {
            Thread carro = new CruzamentoController(idCarro, semaforo);
            carro.start();
        }
    }
}

