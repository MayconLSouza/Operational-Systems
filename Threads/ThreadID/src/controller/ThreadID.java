package controller;

//extends Thread
//Parâmetros por construtor
//método run()

public class ThreadID extends Thread {

	private int idThread;
	
	//construtor
	public ThreadID(int idThread) {
		this.idThread = idThread;
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + idThread + " ==> TID #" + getId());
	}

}
