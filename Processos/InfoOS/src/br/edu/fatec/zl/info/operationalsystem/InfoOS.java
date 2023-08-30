package br.edu.fatec.zl.info.operationalsystem;

public class InfoOS {

	public InfoOS() {
		super();
	}
	
	//Retorna o sistema operacional que está em execução na máquina
	private void InfoOS() {
		String os = System.getProperty("os.name");				//Nome do s.o.
		String arch = System.getProperty("os.arch");			//Arquitetura do s.o.
		String version = System.getProperty("os.version");		//Versão do s.o.
		System.out.println(os + " - v." + version + " - arch. "+arch);
	}
	
}
