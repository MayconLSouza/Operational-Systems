package view;

import java.util.Arrays;

import controller.ThreadSoma;

public class Principal {

	public static void main(String[] args) {
		
		int[][] matriz = new int [3][5];
		int linha, coluna;
		
		//Preencher matriz
		for( linha = 0 ; linha < 3 ; linha ++) {
			for( coluna = 0 ; coluna < 5 ; coluna ++) {
				matriz[linha][coluna] = (int)(Math.random()*100 + 1);
			}
		}
		
		// Imprimir a matriz
        for ( linha = 0; linha < 3; linha++) {
            System.out.println(Arrays.toString(matriz[linha]));
        }

        // Iniciar Thread
		for ( linha = 0 ; linha < 3 ; linha++ ) {
			Thread threadSoma = new ThreadSoma(linha, matriz[linha]);
			threadSoma.start();
		}

	}

}
