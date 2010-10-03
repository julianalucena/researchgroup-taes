package br.ufpe.cin.in980.linhapesquisa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.util.HttpServletComum;

public class CadastrarLinhaPesquisaServlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;

	public CadastrarLinhaPesquisaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LinhaPesquisa linhaPesquisa = encapsularEmLinhaPesquisa(request);
		Fachada fachada = Fachada.obterInstancia();
		
		try {
			fachada.cadastrarLinhaPesquisa(linhaPesquisa);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}

	private LinhaPesquisa encapsularEmLinhaPesquisa(HttpServletRequest request) {
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
		
		LinhaPesquisa linhaPesquisa = new LinhaPesquisa(null, titulo,
				breveDescricao, detalhadaDescricao, financiadores,
				linksRelacionados, membros, publicacoes);
		
		return linhaPesquisa;
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