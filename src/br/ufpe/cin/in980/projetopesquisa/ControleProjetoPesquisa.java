package br.ufpe.cin.in980.projetopesquisa;

import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class ControleProjetoPesquisa {

	private ProjetoPesquisaDAO projetoPesquisaDAO;

	public ControleProjetoPesquisa(JDBCConnection conexao) {
		this.projetoPesquisaDAO = new ProjetoPesquisaDAO(conexao);
	}

	public void cadastrarProjetoPesquisa(ProjetoPesquisa projetoPesquisa)
			throws Exception {
		if (eInValido(projetoPesquisa)) {
			throw new Exception("Projeto de pesquisa invalido!");
		}
		this.projetoPesquisaDAO.cadastrarProjetoPesquisa(projetoPesquisa);
	}

	private boolean eInValido(ProjetoPesquisa projetoPesquisa) {
		return projetoPesquisa.getNomeProjeto() == null
				|| projetoPesquisa.getNomeProjeto().isEmpty()
				|| projetoPesquisa.getDescricaoProjeto() == null
				|| projetoPesquisa.getDescricaoProjeto().isEmpty();
	}

	public List<ProjetoPesquisa> listarProjetosPesquisa() throws Exception {
		return this.projetoPesquisaDAO.listarProjetosPesquisa();
	}
}