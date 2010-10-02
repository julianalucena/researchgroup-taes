package br.ufpe.cin.in980.pdf;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.io.IOException;
import javax.servlet.ServletException;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.publicacao.ControlePublicacao;
import br.ufpe.cin.in980.publicacao.PublicacaoDAO;

public privileged aspect AspectPDF {

	public byte[] Fachada.getPDF(Long idPublicacao) throws Exception {
		if (idPublicacao == null) {
			return null;
		}
		this.conexao.createConnection();
		ControlePublicacao controlePublicacao = new ControlePublicacao(
				this.conexao);
		byte[] retorno = controlePublicacao.getPDF(idPublicacao);
		this.conexao.closeConnection();
		return retorno;
	}

	public void DownloadPDFServlet.doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idPublicacao = new Long(request.getParameter("idpublicacao"));
		OutputStream out = response.getOutputStream();
		Fachada fachada = Fachada.obterInstancia();
		byte[] b;
		try {
			b = fachada.getPDF(idPublicacao);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment; filename=main");
			response.setCharacterEncoding("UTF-8");
			out.write(b, 0, b.length);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}

	public byte[] ControlePublicacao.getPDF(Long idPublicacao) throws Exception {
		if (idPublicacao < 1) {
			throw new Exception("PDf invalido");
		}
		return this.publicacaoDAO.getPDF(idPublicacao);
	}

	public byte[] PublicacaoDAO.getPDF(Long idPublicacao) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT pdf FROM publicacao WHERE idpublicacao = ?");
		stat.setLong(1, idPublicacao);
		ResultSet tab = stat.executeQuery();
		byte[] retorno = null;
		if (tab.next()) {
			retorno = tab.getBytes(1);
		}
		tab.close();
		stat.close();
		return retorno;
	}
}