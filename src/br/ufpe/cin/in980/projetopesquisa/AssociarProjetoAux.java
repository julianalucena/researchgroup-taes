package br.ufpe.cin.in980.projetopesquisa;

import br.ufpe.cin.in980.fachada.Fachada;

public class AssociarProjetoAux {

	public static String comboBox() {
		// #ifdef projeto_pesquisa
//@		String retorno = "<tr><td> <select id=\"idProjetoPesquisa\" name=\"idProjetoPesquisa\" >";
//@		try {
//@			for (ProjetoPesquisa projetoPesquisa : Fachada.obterInstancia()
//@					.listarProjetosPesquisa()) {
//@				retorno += "<option value=\""
//@						+ projetoPesquisa.getIdProjetoPesquisa() + "\">"
//@						+ projetoPesquisa.getNomeProjeto() + "</option>";
//@			}
//@		} catch (Exception e) {
//@			e.printStackTrace();
//@		}
//@		retorno += "</select> </td></tr>";
//@		return retorno;
		// #else
		 return "";
		// #endif
	}
}
