package br.ufpe.cin.in980.publicacao;

import java.sql.SQLException;
import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class ControlePublicacao {

	private PublicacaoDAO publicacaoDAO;

	public ControlePublicacao(JDBCConnection conexao) throws SQLException {
		this.publicacaoDAO = new PublicacaoDAO(conexao);
	}

	public void cadastrarPublicacao(Publicacao publicacao) throws Exception {
		if (isPublicacaoInValida(publicacao)) {
			throw new Exception("Publica��o inv�lida!");
		}
		this.publicacaoDAO.cadastrarPublicacao(publicacao);
	}

	public List<PublicacaoAOM> buscarPublicacoesAOM(String termo) throws Exception {
		// if (termo.isEmpty()) {
		// throw new Exception("Termo inv�lido!");
		// }
		//////////return this.publicacaoDAO.buscarPublicacoes(termo);
		return this.publicacaoDAO.buscarPublicacoesAOM(termo);
	}
	
	public List<Publicacao> buscarPublicacoes(String termo) throws Exception {
		// if (termo.isEmpty()) {
		// throw new Exception("Termo inv�lido!");
		// }
		//////////return this.publicacaoDAO.buscarPublicacoes(termo);
		return this.publicacaoDAO.buscarPublicacoes(termo);
	}

	public Publicacao buscarPublicacao(Long idPublicacao) throws Exception {
		if (idPublicacao < 1) {
			throw new Exception("ID inv�lido!");
		}
		return this.publicacaoDAO.buscarPublicacao(idPublicacao);
	}

	public void editarPublicacao(Publicacao publicacao) throws Exception {
		if (isPublicacaoInValida(publicacao)) {
			throw new Exception("Publica��o inv�lida!");
		}
		this.publicacaoDAO.editarPublicacao(publicacao);
	}

	private boolean isPublicacaoInValida(Publicacao publicacao) {
		return publicacao.getAno() < 1
				|| publicacao.getAutoresMembros() == null
				|| publicacao.getAutoresMembros().isEmpty()
				|| publicacao.getPdf() == null
				|| publicacao.getPdf().length < 1
				|| publicacao.getTitulo() == null
				|| publicacao.getTitulo().isEmpty();
	}
}