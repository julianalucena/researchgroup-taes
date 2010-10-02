package br.ufpe.cin.in980.publicacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CadastrarPublicacaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CadastrarPublicacaoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PublicacaoAux publicacaoAux = new PublicacaoAux();
		Publicacao publicacao = publicacaoAux.publicacaoAux(request, response);
		HttpSession session = request.getSession(true);
		session.setAttribute("publicacao", publicacao);
		request.getRequestDispatcher("cadastrar_publicacao2.jsp").forward(
				request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}