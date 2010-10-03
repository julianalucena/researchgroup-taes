package br.ufpe.cin.in980.linhapesquisa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

public class RemoverLinhaPesquisaServlet extends HttpServletComum {

	private static final long serialVersionUID = 1L;

	public RemoverLinhaPesquisaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idLinhaPublicacao = new Long(request
				.getParameter("idlinhapesquisa"));
		LinhaPesquisa linhaPesquisa = new LinhaPesquisa();
		linhaPesquisa.setIdLinhaPesquisa(idLinhaPublicacao);
		Fachada fachada = Fachada.obterInstancia();
		try {
			fachada.deletarLinhaPesquisa(linhaPesquisa);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}