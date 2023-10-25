package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroupFruitsController implements IGroupFruitsController{

	public GroupFruitsController() {
		super();
	}

	@Override
	public void readFile(String path, String name) throws IOException {
		//Define arquivo
		File arq = new File(path, name);
		//Verifico se o arquivo existe
		if(arq.exists() && arq.isFile()) {
			//abre arquivo
			FileInputStream fluxo = new FileInputStream(arq);
			//le arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo);
			//converte arquivo
			BufferedReader buffer = new BufferedReader(leitor);
			//coloca o conteudo no buffer
			String linha = buffer.readLine();
			while(linha != null) {	//procura EOF(end of file)
				//separa o conteudo da linha pela virgula e coloca em um vetor
				String[] group = linha.split(",");
				//se o grupo for fruta
				if(group[2].equals("Fruits")) {
					//exibe informacoes da fruta
					System.out.println(group[0] + "\t" + group[1] + "\t" + group[3] + "\n");
				}
				//le proxima linha
				linha = buffer.readLine();
			}
			//fecha arquivo
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}

}
