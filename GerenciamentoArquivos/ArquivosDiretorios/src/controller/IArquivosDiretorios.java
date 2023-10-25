package controller;

import java.io.IOException;

public interface IArquivosDiretorios {

	public void leDiretorio(String path)
		throws IOException;
	public void criaTxt(String path, String nome)
		throws IOException;
	public void leTxt(String absolutePath)
		throws IOException;
	public void abreArquivoApp(String absolutePath)
		throws IOException;
	public void propriedades(String absolutePath)
		throws IOException;
	
}
