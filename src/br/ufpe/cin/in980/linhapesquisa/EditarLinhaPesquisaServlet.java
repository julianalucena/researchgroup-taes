package br.ufpe.cin.in980.linhapesquisa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.publicacao.Publicacao;

public class EditarLinhaPesquisaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EditarLinhaPesquisaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idLinhaPesquisa = new Long(request.getParameter("idLinhaPesquisa"));
		String titulo = request.getParameter("titulo");
		String breveDescricao = request.getParameter("breve_descricao");
		String detalhadaDescricao = request.getParameter("detalhada_descricao");
		String financiadores = request.getParameter("financiadores");
		String linksRelacionados = request.getParameter("links_relacionados");
		String[] idMembros = request.getParameterValues("membros");
		String[] idPublicacoes = request.getParameterValues("publicacoes");
		List<Membro> membros = new ArrayList<Membro>();
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		preencherMembros(idMembros, membros);
		preencherPublicacoes(idPublicacoes, publicacoes);
		LinhaPesquisa linhaPesquisa = new LinhaPesquisa(idLinhaPesquisa,
				titulo, breveDescricao, detalhadaDescricao, financiadores,
				linksRelacionados, membros, publicacoes);
		Fachada fachada = Fachada.obterInstancia();
		try {
			fachada.editarLinhaPesquisa(linhaPesquisa);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private void preencherPublicacoes(String[] idPublicacoes,
			List<Publicacao> publicacoes) {
		for (String idPublicacao : idPublicacoes) {
			Publicacao publicacao = new Publicacao();
			publicacao.setIdPublicacao(new Long(idPublicacao));
			publicacoes.add(publicacao);
		}
	}

	private void preencherMembros(String[] idMembros, List<Membro> membros) {
		for (String idMembro : idMembros) {
			Membro membro = new Membro();
			membro.setIdMembro(new Long(idMembro));
			membros.add(membro);
		}
	}
}