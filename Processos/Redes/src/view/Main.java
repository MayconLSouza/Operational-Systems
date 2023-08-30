package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		
		int OpcaoMenu = 0;
		String MenuText = 	"""
							MENU DE OPÇÕES
							==========================================
							1 - Informações de IP
							2 - Testar Ping
							9 - Finalizar
							""";
		
		RedesController methods = new RedesController();
		
		while(OpcaoMenu != 9){
			OpcaoMenu = Integer.parseInt(JOptionPane.showInputDialog(MenuText));
			switch(OpcaoMenu) {
				case 1:
					//Chamada do método que fornece informações do sistema operacional
					methods.os();
					//Chamada do método que  verifica configuração do ip
					methods.ip();
					break;
				case 2:
					//Chamada do método que fornece informações do sistema operacional
					methods.os();
					//Chamada do método que verifica como está o ping
					methods.ping();
					break;
				case 9:
					System.exit(0);
					break;
				default:
					JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
			}
		}
		
	}

}
