package br.ufpe.cin.in980.linhapesquisa;

import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class ControleLinhaPesquisa {

	private LinhaPesquisaDAO linhaPesquisaDAO;

	public ControleLinhaPesquisa(JDBCConnection conexao) {
		this.linhaPesquisaDAO = new LinhaPesquisaDAO(conexao);
	}

	public void cadastrarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		if (ehLinhaPesquisaInvalida(linhaPesquisa)) {
			throw new Exception("Linha de pesquisa inválida");
		}
		this.linhaPesquisaDAO.cadastrarLinhaPesquisa(linhaPesquisa);
	}

	public List<LinhaPesquisa> buscarLinhasPesquisa(String termo)
			throws Exception {
		return this.linhaPesquisaDAO.buscarLinhasPesquisa(termo);
	}

	public void editarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		if (ehLinhaPesquisaInvalida(linhaPesquisa)) {
			throw new Exception("Linha de pesquisa inválida!");
		}
		this.linhaPesquisaDAO.editarLinhaPesquisa(linhaPesquisa);
	}

	public void deletarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		if (linhaPesquisa.getIdLinhaPesquisa() == null
				|| linhaPesquisa.getIdLinhaPesquisa() < 1) {
			throw new Exception("Linha de pesquisa inválida!");
		}
		this.linhaPesquisaDAO.deletarLinhaPesquisa(linhaPesquisa);
	}

	private boolean ehLinhaPesquisaInvalida(LinhaPesquisa linhaPesquisa) {
		return linhaPesquisa.getTitulo() == null
				|| linhaPesquisa.getTitulo().isEmpty()
				|| linhaPesquisa.getBreveDescricao() == null
				|| linhaPesquisa.getTitulo().isEmpty()
				|| linhaPesquisa.getDetalhadaDescricao() == null
				|| linhaPesquisa.getDetalhadaDescricao().isEmpty()
				|| linhaPesquisa.getLinksRelacionados() == null
				|| linhaPesquisa.getLinksRelacionados().isEmpty()
				|| linhaPesquisa.getFinanciadores() == null
				|| linhaPesquisa.getFinanciadores().isEmpty();
		// || linhaPesquisa.getMembros() == null
		// || linhaPesquisa.getMembros().isEmpty()
		// || linhaPesquisa.getPublicacoes() == null
		// || linhaPesquisa.getPublicacoes().isEmpty();
	}
}