package br.ufpe.cin.in980.publicacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

public class BuscarPublicacaoServlet extends HttpServletComum {

	private static final long serialVersionUID = 1L;

	public BuscarPublicacaoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String termo = request.getParameter("termo");
		Fachada fachada = Fachada.obterInstancia();
		List<PublicacaoAOM> publicacoes;
		try {
			publicacoes = fachada.buscarPublicacoes(termo);
			HttpSession session = request.getSession(true);
			session.setAttribute("publicacoes", publicacoes);
			request.getRequestDispatcher("buscar_publicacao.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}
