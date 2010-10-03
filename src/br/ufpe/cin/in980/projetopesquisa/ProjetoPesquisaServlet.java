package br.ufpe.cin.in980.projetopesquisa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

public class ProjetoPesquisaServlet extends HttpServletComum {

	private static final long serialVersionUID = 1L;

	public ProjetoPesquisaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nomeProjetoPesquisa = request.getParameter("nome");
		String descProjetoPesquisa = request.getParameter("desc");
		ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa();
		projetoPesquisa.setNomeProjeto(nomeProjetoPesquisa);
		projetoPesquisa.setDescricaoProjeto(descProjetoPesquisa);
		Fachada fachada = Fachada.obterInstancia();
		try {
			fachada.cadastrarProjetoPesquisa(projetoPesquisa);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}