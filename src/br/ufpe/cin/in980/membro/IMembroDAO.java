package br.ufpe.cin.in980.membro;

import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public interface IMembroDAO {

	public void setConexao(JDBCConnection conexao);
	
	public void cadastrarMembro(Membro membro) throws Exception;
	public List<Membro> buscarMembro(String termo) throws Exception;
	public void editarMembro(Membro membro) throws Exception;
	public void deletarMembro(Membro membro) throws Exception;
	public List<Membro> listarMembros() throws Exception;
	public List<ProfessorPesquisador> listarProfessores() throws Exception;
	public List<ProfessorPesquisador> listarPesquisadores() throws Exception;
	public List<Estudante> listarEstudantes() throws Exception;
	public void adicionarLinhasPesquisa(Membro membro) throws Exception;
	
}
