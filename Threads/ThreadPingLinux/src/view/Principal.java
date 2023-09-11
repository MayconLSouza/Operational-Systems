package view;

import controller.ThreadPing;

public class Principal {
	
	public static void main(String[] args) {

		Thread uolThread = new ThreadPing("www.uol.com.br");
        Thread terraThread = new ThreadPing("www.terra.com.br");
        Thread googleThread = new ThreadPing("www.google.com.br");

        uolThread.start();
        terraThread.start();
        googleThread.start();
	}
}