package controller;

import java.util.concurrent.Semaphore;

public class CruzamentoController extends Thread {

    private String[] sentido = {"norte", "sul", "leste", "oeste"};
    private int idCarro;
    private Semaphore semaforo;

    public CruzamentoController(int idCarro, Semaphore semaforo) {
        this.idCarro = idCarro;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            // Início da seção crítica: adquire o semáforo para entrar no cruzamento
            semaforo.acquire();
            // Consegue atravessar o semáforo
            System.out.println("#Carro " + (idCarro + 1) + " cruzou o semáforo no sentido " + sentido[idCarro]);
        } catch (InterruptedException e) {
            String msgError = e.getMessage();
            System.err.println(msgError);
        } finally {
            // Fim da seção crítica: libera o semáforo após passar pelo cruzamento
            semaforo.release();
        }
    }
}

