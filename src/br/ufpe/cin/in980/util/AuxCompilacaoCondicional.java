package br.ufpe.cin.in980.util;

//#ifdef linha_pesquisa

//#endif
public class AuxCompilacaoCondicional {

	public static String adicionarMenu() {
		String retorno = "";
		// #ifdef linha_pesquisa
		MessageProperties prop = new MessageProperties();
		retorno = "<li> <a href=\"cadastrar_linhapesquisa.jsp\">"
				+ prop.getCaptions().getString("cadastrarLinhaPesquisaKey")
				+ " </a> </li><li> <a href=\"buscar_linhapesquisa.jsp\">"
				+ prop.getCaptions().getString("buscarLinhaPesquisaKey")
				+ "</a> </li><li> <a href=\"linhas_pesquisa.jsp\">"
				+ prop.getCaptions().getString("listarLinhasPesquisaKey")
				+ "</a> </li>";
		// #endif
		return retorno;
	}
}