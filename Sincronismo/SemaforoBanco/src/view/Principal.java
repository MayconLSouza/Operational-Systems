package view;

import controller.BancoController;

public class Principal {

    public static void main(String[] args) {
        
        int numTransacoes = 20; // Alterado para 20 transações
        Thread[] threads = new Thread[numTransacoes]; // Array para armazenar as threads

        for (int transacao = 1000; transacao < 1020; transacao++) {
            int idConta = (int) (Math.random() * 40 + 10);
            double saldoConta = (double) (Math.round(Math.random() * 9900 + 101));
            double valor = (double) (Math.round(Math.random() * 900 + 100));
            threads[transacao - 1000] = new BancoController(transacao, idConta, saldoConta, valor);
        }

        // Inicie as threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Aguarde a conclusão de todas as threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                String msgError = e.getMessage();
                System.err.println(msgError);
            }
        }
        
        System.out.println("\nTodas as transações foram concluídas.");
    }
}

