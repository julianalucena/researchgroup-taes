package br.ufpe.cin.in980.projetopesquisa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class ProjetoPesquisaDAO {

	private JDBCConnection conexao;

	public ProjetoPesquisaDAO(JDBCConnection conexao) {
		this.conexao = conexao;
	}

	public void cadastrarProjetoPesquisa(ProjetoPesquisa projetoPesquisa)
			throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"INSERT INTO projeto_pesquisa(nome, descricao) VALUES (?, ?)");
		stat.setString(1, projetoPesquisa.getNomeProjeto());
		stat.setString(2, projetoPesquisa.getDescricaoProjeto());
		stat.executeUpdate();
		stat.close();
	}

	public List<ProjetoPesquisa> listarProjetosPesquisa() throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM projeto_pesquisa");
		ResultSet tab = stat.executeQuery();
		List<ProjetoPesquisa> retorno = new ArrayList<ProjetoPesquisa>();
		while (tab.next()) {
			ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa();
			projetoPesquisa.setIdProjetoPesquisa(tab.getLong(1));
			projetoPesquisa.setNomeProjeto(tab.getString(2));
			projetoPesquisa.setDescricaoProjeto(tab.getString(3));
			retorno.add(projetoPesquisa);
		}
		tab.close();
		stat.close();
		return retorno;
	}
}