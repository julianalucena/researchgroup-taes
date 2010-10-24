package br.ufpe.cin.in980.projetopesquisa;

//#ifdef linha_pesquisa
//@import br.ufpe.cin.in980.util.MessageProperties;
//@
//#endif
public class AuxMenuProjetoPesquisa {

	public static String adicionarMenu() {
		String retorno = "";
		// #ifdef projeto_pesquisa
//@		MessageProperties prop = new MessageProperties();
//@		retorno = "<li> <a href=\"cadastrar_projetopesquisa.jsp\">"
//@				+ prop.getCaptions().getString("cadastrarProjetoPesquisaKey")
//@				+ " </a> </li>";
		// #endif
		return retorno;
	}
}
