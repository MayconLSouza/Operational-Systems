package view;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		
		KillController methods = new KillController();
		String pid = "";
		String nameProcess = "Notepad.exe";

		methods.os();
		methods.listaProcessos();
		//methods.mataPid(pid);
		methods.mataNome(nameProcess);
		
	}

}
