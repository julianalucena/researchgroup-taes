package br.ufpe.cin.in980.linhapesquisa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.util.JDBCConnection;

public class LinhaPesquisaDAO {

	private JDBCConnection conexao;

	public LinhaPesquisaDAO(JDBCConnection conexao) {
		this.conexao = conexao;
	}

	public void cadastrarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		PreparedStatement stat = this.conexao
				.getConnection()
				.prepareStatement(
						"INSERT INTO linha_pesquisa(titulo, breve_descricao, "
								+ "detalhada_descricao, financiadores, links_relacionados) "
								+ "VALUES (?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
		stat.setString(1, linhaPesquisa.getTitulo());
		stat.setString(2, linhaPesquisa.getBreveDescricao());
		stat.setString(3, linhaPesquisa.getDetalhadaDescricao());
		stat.setString(4, linhaPesquisa.getFinanciadores());
		stat.setString(5, linhaPesquisa.getLinksRelacionados());
		stat.executeUpdate();
		ResultSet tab = stat.getGeneratedKeys();
		Long idLinhaPesquisa = 0l;
		if (tab.next()) {
			idLinhaPesquisa = tab.getLong(1);
		}
		linhaPesquisa.setIdLinhaPesquisa(idLinhaPesquisa);
		cadastrarRelacionamentos(linhaPesquisa);
		tab.close();
		stat.close();
	}

	private void cadastrarRelacionamentos(LinhaPesquisa linhaPesquisa)
			throws SQLException {
		if (!linhaPesquisa.getMembros().isEmpty()) {
			cadastrarMembros(linhaPesquisa);
		}
		if (!linhaPesquisa.getPublicacoes().isEmpty()) {
			cadastrarPublicacoes(linhaPesquisa);
		}
	}

	public List<LinhaPesquisa> buscarLinhasPesquisa(String termo)
			throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM linha_pesquisa WHERE titulo LIKE '%' ? '%'",
				PreparedStatement.RETURN_GENERATED_KEYS);
		stat.setString(1, termo);
		ResultSet tab = stat.executeQuery();
		List<LinhaPesquisa> retorno = new ArrayList<LinhaPesquisa>();
		while (tab.next()) {
			LinhaPesquisa linhaPesquisa = encapsularLinhaPesquisa(tab);
			retorno.add(linhaPesquisa);
		}
		tab.close();
		stat.close();
		return retorno;
	}

	private LinhaPesquisa encapsularLinhaPesquisa(ResultSet tab)
			throws SQLException {
		LinhaPesquisa linhaPesquisa = new LinhaPesquisa();
		linhaPesquisa.setIdLinhaPesquisa(tab.getLong(1));
		linhaPesquisa.setTitulo(tab.getString(2));
		linhaPesquisa.setBreveDescricao(tab.getString(3));
		linhaPesquisa.setDetalhadaDescricao(tab.getString(4));
		linhaPesquisa.setFinanciadores(tab.getString(5));
		linhaPesquisa.setLinksRelacionados(tab.getString(6));
		preencherMembros(linhaPesquisa);
		preencherPublicacoes(linhaPesquisa);
		return linhaPesquisa;
	}

	public void editarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		PreparedStatement stat = this.conexao
				.getConnection()
				.prepareStatement(
						"UPDATE linha_pesquisa SET titulo = ?, breve_descricao = ?, "
								+ "detalhada_descricao = ?, financiadores = ?, links_relacionados = ? "
								+ "WHERE idLinhaPesquisa = ?");
		stat.setString(1, linhaPesquisa.getTitulo());
		stat.setString(2, linhaPesquisa.getBreveDescricao());
		stat.setString(3, linhaPesquisa.getDetalhadaDescricao());
		stat.setString(4, linhaPesquisa.getFinanciadores());
		stat.setString(5, linhaPesquisa.getLinksRelacionados());
		stat.setLong(6, linhaPesquisa.getIdLinhaPesquisa());
		stat.executeUpdate();
		// TODO fazer atualizacao pra membros e publicacoes
		stat.close();
	}

	public void deletarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"DELETE FROM linha_pesquisa WHERE idLinhaPesquisa = ?");
		stat.setLong(1, linhaPesquisa.getIdLinhaPesquisa());
		stat.executeUpdate();
		stat.close();
	}

	private void preencherMembros(LinhaPesquisa linhaPesquisa)
			throws SQLException {
		PreparedStatement stat2 = this.conexao
				.getConnection()
				.prepareStatement(
						"SELECT nome FROM membro m JOIN membro_linhapesquisa mp "
								+ "ON mp.idmembro=m.idmembro WHERE mp.idLinhaPesquisa = ?");
		stat2.setLong(1, linhaPesquisa.getIdLinhaPesquisa());
		ResultSet tab2 = stat2.executeQuery();
		List<Membro> membros = new ArrayList<Membro>();
		while (tab2.next()) {
			Membro membro = new Membro();
			membro.setNomeMembro(tab2.getString(1));
			membros.add(membro);
		}
		linhaPesquisa.setMembros(membros);
		stat2.close();
		tab2.close();
	}

	private void preencherPublicacoes(LinhaPesquisa linhaPesquisa)
			throws SQLException {
		PreparedStatement stat2 = this.conexao
				.getConnection()
				.prepareStatement(
						"SELECT titulo FROM publicacao p JOIN publicacao_linhapesquisa mp "
								+ "ON mp.idpublicacao=p.idpublicacao WHERE mp.idLinhaPesquisa = ?");
		stat2.setLong(1, linhaPesquisa.getIdLinhaPesquisa());
		ResultSet tab2 = stat2.executeQuery();
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		while (tab2.next()) {
			Publicacao publicacao = new Publicacao();
			publicacao.setTitulo(tab2.getString(1));
			publicacoes.add(publicacao);
		}
		linhaPesquisa.setPublicacoes(publicacoes);
		stat2.close();
		tab2.close();
	}

	private void cadastrarPublicacoes(LinhaPesquisa linhaPesquisa)
			throws SQLException {
		PreparedStatement stat2 = this.conexao.getConnection()
				.prepareStatement(
						"INSERT INTO publicacao_linhapesquisa(idLinhaPesquisa, idPublicacao) "
								+ "VALUES (?, ?)");
		stat2.setLong(1, linhaPesquisa.getIdLinhaPesquisa());
		for (Publicacao publicacao : linhaPesquisa.getPublicacoes()) {
			stat2.setLong(2, publicacao.getIdPublicacao());
			stat2.executeUpdate();
		}
		stat2.close();
	}

	private void cadastrarMembros(LinhaPesquisa linhaPesquisa)
			throws SQLException {
		PreparedStatement stat2 = this.conexao.getConnection()
				.prepareStatement(
						"INSERT INTO membro_linhapesquisa(idLinhaPesquisa, idMembro) "
								+ "VALUES (?, ?)");
		stat2.setLong(1, linhaPesquisa.getIdLinhaPesquisa());
		for (Membro membro : linhaPesquisa.getMembros()) {
			stat2.setLong(2, membro.getIdMembro());
			stat2.executeUpdate();
		}
		stat2.close();
	}
}