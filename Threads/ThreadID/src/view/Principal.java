package view;

import controller.ThreadID;

public class Principal {

	public static void main(String[] args) {
		
		for (int idThread = 0 ; idThread < 5 ; idThread++ ) {
			Thread threadId = new ThreadID(idThread);
			threadId.start();
		}

	}

}
