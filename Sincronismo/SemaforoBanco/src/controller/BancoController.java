package controller;

import java.util.concurrent.Semaphore;

public class BancoController extends Thread {

    private int transacao;
    private int idConta;
    private double saldoConta;
    private double valor;
    private Semaphore saque = new Semaphore(1); // Semáforo para controle de saques
    private Semaphore deposito = new Semaphore(1); // Semáforo para controle de depósitos

    public BancoController(int transacao, int idConta, double saldoConta, double valor) {
        this.transacao = transacao;
        this.idConta = idConta;
        this.saldoConta = saldoConta;
        this.valor = valor;
    }

    @Override
    public void run() {

        if ((transacao % 2) == 0) {
            saque();
        } else {
            deposito();
        }

    }

    private void saque() {

        try {
            saque.acquire(); // Aguarda permissão para acessar a seção crítica
            // Simular o tempo de processamento da transação
            Thread.sleep(100);
            if (valor <= saldoConta) {
                saldoConta -= valor;
                System.out.println("\nTransacao #" + transacao + " realizada com sucesso\nValor do saque = " + valor + "\nSaldo da conta " + idConta + " atualizado = " + saldoConta);
            } else {
                System.out.println("\nSaldo da conta insuficiente para realizar a transacao " + transacao);
            }
        } catch (InterruptedException e) {
            String msgError = e.getMessage();
            System.err.println(msgError);
        } finally {
            saque.release(); // Libera a permissão após a conclusão da transação
        }

    }

    private void deposito() {

        try {
            deposito.acquire(); // Aguarda permissão para acessar a seção crítica
            // Simular o tempo de processamento da transação
            Thread.sleep(100);
            saldoConta += valor;
            System.out.println("\nTransacao #" + transacao + " realizada com sucesso\nValor do deposito = " + valor + "\nSaldo da conta " + idConta + " atualizado = " + saldoConta);
        } catch (InterruptedException e) {
            String msgError = e.getMessage();
            System.err.println(msgError);
        } finally {
            deposito.release(); // Libera a permissão após a conclusão da transação
        }

    }

}

