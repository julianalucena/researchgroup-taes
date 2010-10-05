package br.ufpe.cin.in980.membro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.in980.membro.ProfessorPesquisador.TipoVinculo;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.util.JDBCConnection;

public class MembroDAO implements IMembroDAO {

	private JDBCConnection conexao;

	public void setConexao(JDBCConnection conexao) {
		this.conexao = conexao;
	}

//	public MembroDAO(JDBCConnection conexao) {
//		this.conexao = conexao;
//	}

	public void cadastrarMembro(Membro membro) throws Exception {
		if (this.existeMembro(membro)) {
			throw new Exception("Membro existente!");
		}
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"INSERT INTO membro(nome, departamento, universidade, email, "
						+ "telefone, website, cidade, pais, foto, status) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);
		stat.setString(1, membro.getNomeMembro());
		stat.setString(2, membro.getDepartamento());
		stat.setString(3, membro.getUniversidade());
		stat.setString(4, membro.getEmail());
		stat.setString(5, membro.getTelefone());
		stat.setString(6, membro.getWebsite());
		stat.setString(7, membro.getCidade());
		stat.setString(8, membro.getPais());
		stat.setBytes(9, membro.getFoto());
		stat.setString(10, membro.getStatus());
		stat.executeUpdate();
		ResultSet tabela = stat.getGeneratedKeys();
		long idMembro = 0;
		while (tabela.next()) {
			idMembro = tabela.getLong(1);
		}
		tabela.close();
		stat.close();
		definirMembro(membro, idMembro);
	}

	public List<Membro> buscarMembro(String termo) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM membro WHERE nome LIKE '%' ? '%'");
		stat.setString(1, termo);
		ResultSet tab = stat.executeQuery();
		List<Membro> retorno = new ArrayList<Membro>();
		while (tab.next()) {
			Membro membro = new Membro();
			membro.setIdMembro(tab.getLong(1));
			membro.setNomeMembro(tab.getString(2));
			membro.setDepartamento(tab.getString(3));
			membro.setUniversidade(tab.getString(4));
			membro.setEmail(tab.getString(5));
			membro.setTelefone(tab.getString(6));
			membro.setWebsite(tab.getString(7));
			membro.setCidade(tab.getString(8));
			membro.setPais(tab.getString(9));
			membro.setFoto(tab.getBytes(10));
			membro.setStatus(tab.getString(11));

			adicionarPublicacoes(membro);
			// HOOK
			adicionarLinhasPesquisa(membro);
			retorno.add(membro);
		}
		tab.close();
		stat.close();
		return retorno;
	}

	public void editarMembro(Membro membro) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"UPDATE membro SET nome = ?, departamento = ?, universidade = ?, email = ?, "
						+ "telefone = ?, website = ?, cidade = ?, pais = ?, "
						+ "foto = ?, status = ? WHERE idMembro = ?");
		stat.setString(1, membro.getNomeMembro());
		stat.setString(2, membro.getDepartamento());
		stat.setString(3, membro.getUniversidade());
		stat.setString(4, membro.getEmail());
		stat.setString(5, membro.getTelefone());
		stat.setString(6, membro.getWebsite());
		stat.setString(7, membro.getCidade());
		stat.setString(8, membro.getPais());
		stat.setBytes(9, membro.getFoto());
		stat.setString(10, membro.getStatus());
		stat.setLong(11, membro.getIdMembro());
		stat.executeUpdate();
		stat.close();
	}

	public void deletarMembro(Membro membro) throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"DELETE FROM membro WHERE idMembro = ?");
		stat.setLong(1, membro.getIdMembro());
		stat.executeUpdate();
		stat.close();
	}

	public List<Membro> listarMembros() throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM membro");
		ResultSet tab = stat.executeQuery();
		List<Membro> retorno = new ArrayList<Membro>();
		while (tab.next()) {
			Membro membro = new Membro();
			membro.setIdMembro(tab.getLong(1));
			membro.setNomeMembro(tab.getString(2));
			membro.setDepartamento(tab.getString(3));
			membro.setUniversidade(tab.getString(4));
			membro.setEmail(tab.getString(5));
			membro.setTelefone(tab.getString(6));
			membro.setWebsite(tab.getString(7));
			membro.setCidade(tab.getString(8));
			membro.setPais(tab.getString(9));
			membro.setFoto(tab.getBytes(10));
			membro.setStatus(tab.getString(11));
			retorno.add(membro);
		}
		tab.close();
		stat.close();
		return retorno;
	}

	public List<ProfessorPesquisador> listarProfessores() throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT nome, foto, departamento, universidade FROM membro m "
						+ "JOIN professor p ON m.idMembro=p.fk_membro");
		ResultSet tab = stat.executeQuery();
		List<ProfessorPesquisador> retorno = new ArrayList<ProfessorPesquisador>();
		while (tab.next()) {
			ProfessorPesquisador professor = new ProfessorPesquisador();
			professor.setTipoVinculo(TipoVinculo.PROFESSOR);
			professor.setNomeMembro(tab.getString(1));
			professor.setFoto(tab.getBytes(2));
			professor.setDepartamento(tab.getString(3));
			professor.setUniversidade(tab.getString(4));
			retorno.add(professor);
		}
		stat.close();
		tab.close();
		return retorno;
	}

	public List<ProfessorPesquisador> listarPesquisadores() throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT nome, foto, departamento, universidade FROM membro m "
						+ "JOIN pesquisador p ON m.idMembro=p.fk_membro");
		ResultSet tab = stat.executeQuery();
		List<ProfessorPesquisador> retorno = new ArrayList<ProfessorPesquisador>();
		while (tab.next()) {
			ProfessorPesquisador pesquisador = new ProfessorPesquisador();
			pesquisador.setTipoVinculo(TipoVinculo.PESQUISADOR);
			pesquisador.setNomeMembro(tab.getString(1));
			pesquisador.setFoto(tab.getBytes(2));
			pesquisador.setDepartamento(tab.getString(3));
			pesquisador.setUniversidade(tab.getString(4));
			retorno.add(pesquisador);
		}
		stat.close();
		tab.close();
		return retorno;
	}

	public List<Estudante> listarEstudantes() throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT nome, foto, departamento, universidade FROM membro m "
						+ "JOIN estudante p ON m.idMembro=p.fk_membro");
		ResultSet tab = stat.executeQuery();
		List<Estudante> retorno = new ArrayList<Estudante>();
		while (tab.next()) {
			Estudante estudante = new Estudante();
			estudante.setNomeMembro(tab.getString(1));
			estudante.setFoto(tab.getBytes(2));
			estudante.setDepartamento(tab.getString(3));
			estudante.setUniversidade(tab.getString(4));
			retorno.add(estudante);
		}
		stat.close();
		tab.close();
		return retorno;
	}

	private boolean existeMembro(Membro membro) throws SQLException {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT COUNT(nome) FROM membro WHERE nome = ?");
		stat.setString(1, membro.getNomeMembro());
		ResultSet tab = stat.executeQuery();
		if (tab.next()) {
			if (tab.getInt(1) > 0) {
				tab.close();
				stat.close();
				return true;
			}
		}
		tab.close();
		stat.close();
		return false;
	}

	private void definirMembro(Membro membro, long idMembro)
			throws SQLException {
		if (membro instanceof Estudante) {
			this.cadastrarEstudante(idMembro, (Estudante) membro);
		} else if (membro instanceof ProfessorPesquisador) {
			
			TipoVinculo tipoVinculo = ((ProfessorPesquisador) membro).getTipoVinculo();
			
			if (tipoVinculo.equals(TipoVinculo.PESQUISADOR)) {
				this.cadastrarPesquisador(idMembro, (ProfessorPesquisador) membro);				
			} else {				
				this.cadastrarProfessor(idMembro, (ProfessorPesquisador) membro);
			}
		}
	}

	private void cadastrarEstudante(long idMembro, Estudante estudante)
			throws SQLException {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"INSERT INTO estudante(nome_orientador, "
						+ "nome_coorientador, tipo_estudante, FK_membro) "
						+ "VALUES (?, ?, ?, ?)");
		stat.setString(1, estudante.getNomeOrientador());
		stat.setString(2, estudante.getNomeCoorientador());
		stat.setString(3, estudante.getTipoEstudante().toString());
		stat.setLong(4, idMembro);
		stat.executeUpdate();
		stat.close();
	}

	private void cadastrarProfessor(long idMembro, ProfessorPesquisador professor)
			throws SQLException {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"INSERT INTO professor(FK_membro) VALUES (?)");
		stat.setLong(1, idMembro);
		stat.executeUpdate();
		stat.close();
	}

	private void cadastrarPesquisador(long idMembro, ProfessorPesquisador pesquisador)
			throws SQLException {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"INSERT INTO pesquisador(FK_membro) VALUES (?)");
		stat.setLong(1, idMembro);
		stat.executeUpdate();
		stat.close();
	}

	private void adicionarPublicacoes(Membro membro) throws SQLException {
		PreparedStatement stat2 = this.conexao
				.getConnection()
				.prepareStatement(
						"SELECT * FROM membro_publicacao mp "
								+ "JOIN publicacao p ON mp.idpublicacao = p.idpublicacao "
								+ "WHERE mp.idmembro = ?");
		stat2.setLong(1, membro.getIdMembro());
		ResultSet tab2 = stat2.executeQuery();
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		while (tab2.next()) {
			Publicacao publicacao = new Publicacao();
			publicacao.setIdPublicacao(tab2.getLong(3));
			publicacao.setTitulo(tab2.getString(4));
			publicacao.setAno(tab2.getInt(5));
			publicacao.setPdf(tab2.getBytes(6));
			publicacoes.add(publicacao);
		}
		membro.setPublicacoes(publicacoes);
		stat2.close();
		tab2.close();
	}

	// HOOK
	public void adicionarLinhasPesquisa(Membro membro) throws Exception {

	}
}