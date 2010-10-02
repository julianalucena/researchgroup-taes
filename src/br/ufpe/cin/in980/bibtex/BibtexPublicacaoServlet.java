package br.ufpe.cin.in980.bibtex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.publicacao.Publicacao;

public class BibtexPublicacaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BibtexPublicacaoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idPublicacao = new Long(request.getParameter("idpublicacao"));
		Fachada fachada = Fachada.obterInstancia();
		try {
			Publicacao publicacao = fachada.buscarPublicacao(idPublicacao);
			StringBuffer bib = new StringBuffer();
			new GerarListaPublicacaoAux().gerarPublicacaoBibTex(publicacao, bib);
			HttpSession session = request.getSession(true);
			session.setAttribute("bib", bib.toString());
			request.getRequestDispatcher("bibtex.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}