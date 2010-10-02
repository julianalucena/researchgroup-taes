package br.ufpe.cin.in980.publicacao;

import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.ufpe.cin.in980.fachada.Fachada;
import java.io.IOException;
import javax.servlet.ServletException;

public privileged aspect AspectPublicacao {

	public void RemoverPublicacaoServlet.doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idPublicacao = request.getParameter("idpublicacao");
		Publicacao publicacao = new Publicacao();
		publicacao.setIdPublicacao(new Long(idPublicacao));
		Fachada fachada = Fachada.obterInstancia();
		try {
			fachada.deletarPublicacao(publicacao);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}

	public void Fachada.deletarPublicacao(Publicacao publicacao)
			throws Exception {
		if (publicacao != null) {
			this.conexao.createConnection();
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			controlePublicacao.deletarPublicacao(publicacao);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}
	}

	public void ControlePublicacao.deletarPublicacao(Publicacao publicacao)
			throws Exception {
		if (publicacao.getIdPublicacao() == null
				|| publicacao.getIdPublicacao() < 1) {
			throw new Exception("ID invalido");
		}
		this.publicacaoDAO.deletarPublicacao(publicacao);
	}

	public void PublicacaoDAO.deletarPublicacao(Publicacao publicacao)
			throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"DELETE FROM publicacao WHERE idPublicacao = ?");
		stat.setLong(1, publicacao.getIdPublicacao());
		stat.executeUpdate();
		stat.close();
	}
}