package br.ufpe.cin.in980.visitante;

//#ifdef visitante
import br.ufpe.cin.in980.util.MessageProperties;

//#endif
public class AuxMenuVisitante {

	public static String adicionarMenu() {
		String retorno = "";
		// #ifdef visitante
		MessageProperties prop = new MessageProperties();
		retorno = "<li> <a href=\"cadastrar_visitante.jsp\">"
				+ prop.getCaptions().getString("cadastrarVisitanteKey")
				+ " </a> </li>";
		// #endif
		return retorno;
	}
	
	public static String adicionarMenuSelecionado() {
		String retorno = "";
		// #ifdef visitante
		MessageProperties prop = new MessageProperties();
		retorno = "<li class=\"menu-selecionado\"> <a href=\"cadastrar_visitante.jsp\">"
				+ prop.getCaptions().getString("cadastrarVisitanteKey")
				+ " </a> </li>";
		// #endif
		return retorno;
	}
}