package br.ufpe.cin.in980.publicacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;
import br.ufpe.cin.in980.publicacao.Monografia.TipoMonografia;
import br.ufpe.cin.in980.util.JDBCConnection;

public class PublicacaoDAO {

	public JDBCConnection conexao;

	public PublicacaoDAO(JDBCConnection conexao) {
		this.conexao = conexao;
	}

	public List<Publicacao> buscarPublicacoes(String termo) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM publicacao WHERE titulo LIKE '%' ? '%'");
		stat.setString(1, termo);
		ResultSet tab = stat.executeQuery();
		List<Publicacao> retorno = new ArrayList<Publicacao>();
		while (tab.next()) {
			Long idPublicacao = tab.getLong(1);
			Publicacao publicacao = definirPublicacao(idPublicacao);
			publicacao.setIdPublicacao(tab.getLong(1));
			publicacao.setTitulo(tab.getString(2));
			publicacao.setAno(tab.getInt(3));
			publicacao.setPdf(tab.getBytes(4));
			adicionarMembrosPublicacao(publicacao);
			adicionarNaoMembrosPublicacao(publicacao);
			retorno.add(publicacao);
		}
		stat.close();
		tab.close();
		return retorno;
	}

	public void cadastrarPublicacao(Publicacao publicacao) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"INSERT INTO publicacao(titulo, ano, pdf) VALUES (?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);
		stat.setString(1, publicacao.getTitulo());
		stat.setInt(2, publicacao.getAno());
		stat.setBytes(3, publicacao.getPdf());
		stat.executeUpdate();
		ResultSet tab = stat.getGeneratedKeys();
		long idPublicacao = 0l;
		if (tab.next()) {
			idPublicacao = tab.getLong(1);
		}
		tab.close();
		stat.close();
		publicacao.setIdPublicacao(idPublicacao);
		this.cadastrarRelacionamentoComMembro(idPublicacao, publicacao
				.getAutoresMembros());
		if (publicacao.getAutoresNaoMembros() != null
				|| !publicacao.getAutoresNaoMembros().isEmpty()) {
			this.cadastrarRelacionamentoComNaoMembro(idPublicacao, publicacao
					.getAutoresNaoMembros());
		}
		this.definirPublicacao(publicacao);
	}

	public void editarPublicacao(Publicacao publicacao) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"UPDATE publicacao SET titulo = ?, ano = ?, pdf = ? "
						+ "WHERE idpublicacao = ?");
		stat.setString(1, publicacao.getTitulo());
		stat.setInt(2, publicacao.getAno());
		stat.setBytes(3, publicacao.getPdf());
		stat.setLong(4, publicacao.getIdPublicacao());
		stat.executeUpdate();
		stat.close();
	}

	public Publicacao buscarPublicacao(Long idPublicacao) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM publicacao WHERE idPublicacao = ?");
		stat.setLong(1, idPublicacao);
		ResultSet tab = stat.executeQuery();
		Publicacao retorno = null;
		if (tab.next()) {
			retorno = definirPublicacao(idPublicacao);
			retorno.setIdPublicacao(idPublicacao);
			retorno.setTitulo(tab.getString(2));
			retorno.setAno(tab.getInt(3));
			retorno.setPdf(tab.getBytes(4));
			adicionarMembrosPublicacao(retorno);
			adicionarNaoMembrosPublicacao(retorno);
		}
		stat.close();
		tab.close();
		return retorno;
	}

	private Publicacao definirPublicacao(Long idPublicacao) throws Exception {
		Publicacao retorno = null;
		retorno = getConferencia(idPublicacao);
		if (retorno != null) {
			return retorno;
		}
		retorno = getPeriodicoRevista(idPublicacao);
		if (retorno != null) {
			return retorno;
		}
		retorno = getTeseDoutorado(idPublicacao);
		if (retorno != null) {
			return retorno;
		}
		retorno = getDissertacaoMestrado(idPublicacao);
		if (retorno != null) {
			return retorno;
		}
		return null;
	}

	private Monografia getDissertacaoMestrado(Long idPublicacao)
			throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM dissertacao_mestrado WHERE fk_publicacao = ?");
		stat.setLong(1, idPublicacao);
		Monografia dissertacaoMestrado = null;
		ResultSet tab = stat.executeQuery();
		if (tab.next()) {
			dissertacaoMestrado = new Monografia();
			dissertacaoMestrado.setTipoMonografia(TipoMonografia.DISSERTACAO_MESTRADO);
			dissertacaoMestrado.setEscola(tab.getString(2));
			dissertacaoMestrado.setMes(tab.getInt(3));
		}
		stat.close();
		tab.close();
		return dissertacaoMestrado;
	}

	private Monografia getTeseDoutorado(Long idPublicacao) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM tese_doutorado WHERE fk_publicacao = ?");
		stat.setLong(1, idPublicacao);
		Monografia teseDoutorado = null;
		ResultSet tab = stat.executeQuery();
		if (tab.next()) {
			teseDoutorado = new Monografia();
			teseDoutorado.setTipoMonografia(TipoMonografia.TESE_DOUTORADO);
			teseDoutorado.setEscola(tab.getString(2));
			teseDoutorado.setMes(tab.getInt(3));
		}
		stat.close();
		tab.close();
		return teseDoutorado;
	}

	private ArtPeriodicoRevista getPeriodicoRevista(Long idPublicacao)
			throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM periodico_revista WHERE fk_publicacao = ?");
		stat.setLong(1, idPublicacao);
		ArtPeriodicoRevista periodicoRevista = null;
		ResultSet tab = stat.executeQuery();
		if (tab.next()) {
			periodicoRevista = new ArtPeriodicoRevista();
			periodicoRevista.setJournal(tab.getString(2));
			periodicoRevista.setVolume(tab.getInt(3));
			periodicoRevista.setNumero(tab.getInt(4));
			periodicoRevista.setPaginas(tab.getInt(5));
		}
		stat.close();
		tab.close();
		return periodicoRevista;
	}

	private ArtConferencia getConferencia(Long idPublicacao) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM conferencia WHERE fk_publicacao = ?");
		stat.setLong(1, idPublicacao);
		ArtConferencia conferencia = null;
		ResultSet tab = stat.executeQuery();
		if (tab.next()) {
			conferencia = new ArtConferencia();
			conferencia.setConferencia(tab.getString(2));
			conferencia.setPaginas(tab.getInt(3));
			conferencia.setMes(tab.getInt(4));
		}
		stat.close();
		tab.close();
		return conferencia;
	}

	private void cadastrarRelacionamentoComMembro(long idPublicacao,
			List<Membro> membros) throws Exception {
		for (Membro membro : membros) {
			PreparedStatement stat = this.conexao
					.getConnection()
					.prepareStatement(
							"INSERT INTO membro_publicacao(idmembro, idpublicacao) VALUES (?, ?)");
			stat.setLong(1, membro.getIdMembro());
			stat.setLong(2, idPublicacao);
			stat.executeUpdate();
			stat.close();
		}
	}

	private void definirPublicacao(Publicacao publicacao) throws SQLException {
		if (publicacao instanceof ArtPeriodicoRevista) {
			this.cadastrarArtPeriodicoRevista((ArtPeriodicoRevista) publicacao);
		} else if (publicacao instanceof ArtConferencia) {
			this.cadastrarArtConferencia((ArtConferencia) publicacao);
		} else if (publicacao instanceof Monografia) {
			TipoMonografia tipoMonografia = ((Monografia) publicacao).getTipoMonografia();
			
			if (tipoMonografia.equals(TipoMonografia.DISSERTACAO_MESTRADO)){
				this.cadastrarDissertacaoMestrado((Monografia) publicacao);
			}else if (tipoMonografia.equals(TipoMonografia.TESE_DOUTORADO)){
				this.cadastrarTeseDoutorado((Monografia) publicacao);				
			}
		}
	}

	private void cadastrarTeseDoutorado(Monografia teseDoutorado)
			throws SQLException {
		PreparedStatement stat = this.conexao
				.getConnection()
				.prepareStatement(
						"INSERT INTO tese_doutorado(escola, mes, fk_publicacao) VALUES (?, ?, ?)");
		stat.setString(1, teseDoutorado.getEscola());
		stat.setInt(2, teseDoutorado.getMes());
		stat.setLong(3, teseDoutorado.getIdPublicacao());
		stat.executeUpdate();
		stat.close();
	}

	private void cadastrarDissertacaoMestrado(
			Monografia dissertacaoMestrado) throws SQLException {
		PreparedStatement stat = this.conexao
				.getConnection()
				.prepareStatement(
						"INSERT INTO dissertacao_mestrado(escola, mes, fk_publicacao) VALUES (?, ?, ?)");
		stat.setString(1, dissertacaoMestrado.getEscola());
		stat.setInt(2, dissertacaoMestrado.getMes());
		stat.setLong(3, dissertacaoMestrado.getIdPublicacao());
		stat.executeUpdate();
		stat.close();
	}

	private void cadastrarArtConferencia(ArtConferencia conferencia)
			throws SQLException {
		PreparedStatement stat = this.conexao
				.getConnection()
				.prepareStatement(
						"INSERT INTO conferencia(conferencia, paginas, mes, fk_publicacao) VALUES(?, ?, ?, ?)");
		stat.setString(1, conferencia.getConferencia());
		stat.setInt(2, conferencia.getPaginas());
		stat.setInt(3, conferencia.getMes());
		stat.setLong(4, conferencia.getIdPublicacao());
		stat.executeUpdate();
		stat.close();
	}

	private void cadastrarArtPeriodicoRevista(
			ArtPeriodicoRevista periodicoRevista) throws SQLException {
		PreparedStatement stat = this.conexao
				.getConnection()
				.prepareStatement(
						"INSERT INTO periodico_revista(journal, volume, numero, paginas, fk_publicacao) "
								+ "VALUES (?, ?, ?, ?, ?)");
		stat.setString(1, periodicoRevista.getJournal());
		stat.setInt(2, periodicoRevista.getVolume());
		stat.setInt(3, periodicoRevista.getNumero());
		stat.setInt(4, periodicoRevista.getPaginas());
		stat.setLong(5, periodicoRevista.getIdPublicacao());
		stat.executeUpdate();
		stat.close();
	}

	private void cadastrarRelacionamentoComNaoMembro(long idPublicacao,
			List<NaoMembro> naoMembros) throws Exception {
		for (NaoMembro naoMembro : naoMembros) {
			PreparedStatement stat = this.conexao.getConnection()
					.prepareStatement(
							"INSERT INTO naomembro_publicacao(idnaomembro, idpublicacao) "
									+ "VALUES (?, ?)");
			stat.setLong(1, naoMembro.getIdNaoMembro());
			stat.setLong(2, idPublicacao);
			stat.executeUpdate();
			stat.close();
		}
	}

	private void adicionarMembrosPublicacao(Publicacao publicacao)
			throws SQLException {
		PreparedStatement stat2 = this.conexao
				.getConnection()
				.prepareStatement(
						"SELECT nome FROM membro m JOIN membro_publicacao mp "
								+ "ON mp.idmembro=m.idmembro WHERE mp.idpublicacao = ?");
		stat2.setLong(1, publicacao.getIdPublicacao());
		ResultSet tab2 = stat2.executeQuery();
		List<Membro> membros = new ArrayList<Membro>();
		while (tab2.next()) {
			Membro membro = new Membro();
			membro.setNomeMembro(tab2.getString(1));
			membros.add(membro);
		}
		publicacao.setAutoresMembros(membros);
		stat2.close();
		tab2.close();
	}

	private void adicionarNaoMembrosPublicacao(Publicacao publicacao)
			throws SQLException {
		PreparedStatement stat3 = this.conexao
				.getConnection()
				.prepareStatement(
						"SELECT nome FROM naomembro m JOIN naomembro_publicacao mp "
								+ "ON mp.idnaomembro=m.idnaomembro JOIN publicacao p "
								+ "ON mp.idpublicacao=p.idpublicacao WHERE p.idpublicacao = ?");
		stat3.setLong(1, publicacao.getIdPublicacao());
		ResultSet tab3 = stat3.executeQuery();
		List<NaoMembro> naoMembros = new ArrayList<NaoMembro>();
		while (tab3.next()) {
			NaoMembro naoMembro = new NaoMembro();
			naoMembro.setNome(tab3.getString(1));
			naoMembros.add(naoMembro);
		}
		publicacao.setAutoresNaoMembros(naoMembros);
		stat3.close();
		tab3.close();
	}

}