package br.ufpe.cin.in980.publicacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;
import br.ufpe.cin.in980.util.HttpServletComum;

public class EditarPublicacao2Servlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;

	public EditarPublicacao2Servlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] idMembros = request.getParameterValues("membros");
		String[] idNaoMembros = request.getParameterValues("naomembros");
		List<Membro> membros = new ArrayList<Membro>();
		List<NaoMembro> naoMembros = new ArrayList<NaoMembro>();
		preencherMembros(idMembros, membros);
		preencherNaoMembros(idNaoMembros, naoMembros);
		HttpSession session = request.getSession(true);
		Publicacao publicacao = (Publicacao) session.getAttribute("publicacao");
		if (publicacao != null) {
			publicacao.setAutoresMembros(membros);
			publicacao.setAutoresNaoMembros(naoMembros);
			Fachada fachada = Fachada.obterInstancia();
			try {
				fachada.editarPublicacao(publicacao);
				request.getRequestDispatcher("sucesso.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("falha.jsp").forward(request,
						response);
			}
		}
	}

	private void preencherNaoMembros(String[] idNaoMembros,
			List<NaoMembro> naoMembros) {
		for (String idNaoMembro : idNaoMembros) {
			NaoMembro naoMembro = new NaoMembro();
			naoMembro.setIdNaoMembro(new Long(idNaoMembro));
			naoMembros.add(naoMembro);
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