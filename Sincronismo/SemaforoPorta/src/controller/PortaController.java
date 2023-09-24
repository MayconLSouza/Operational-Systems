package controller;

import java.util.concurrent.Semaphore;

public class PortaController extends Thread {

    private Semaphore semaforo;  				// Semáforo usado para controlar o acesso à porta
    private int idPessoa;        				// ID da pessoa
    private final int distanciaCorredor = 200;  // Distância total do corredor
    private int distanciaPercorrida = 0;        // Distância percorrida pela pessoa no corredor
    private int tempoPorta;       				// Tempo que a pessoa leva para atravessar a porta

    public PortaController(Semaphore semaforo, int idPessoa) {
        this.semaforo = semaforo;
        this.idPessoa = idPessoa;
    }

    @Override
    public void run() {
    	
        // Simula o movimento da pessoa pelo corredor
        while (distanciaPercorrida < distanciaCorredor) {
            distanciaPercorrida += (int) (Math.random() * 3 + 4);  // Movimento aleatório entre 4 e 6 m/s
        }

        System.out.println("Pessoa #" + idPessoa + " chegou no fim do corredor");

        // Início da seção crítica: adquire o semáforo para entrar na porta
        try {
            semaforo.acquire();  // Tenta adquirir uma permissão do semáforo (bloqueia se não houver permissão disponível)
            tempoPorta = (int) (Math.random() * 1100 + 1000);  // Tempo aleatório para atravessar a porta entre 1 e 2 segundos
            sleep(tempoPorta);  // Simula o tempo de atravessar a porta
        } catch (InterruptedException e) {
            String msgError = e.getMessage();
            System.err.println(msgError);
        } finally {
            System.out.println("Pessoa #" + idPessoa + " cruzou a porta");
            semaforo.release();  // Libera o semáforo após a pessoa passar pela porta
        }
        // Fim da seção crítica: libera o semáforo após passar pela porta
    }
}

