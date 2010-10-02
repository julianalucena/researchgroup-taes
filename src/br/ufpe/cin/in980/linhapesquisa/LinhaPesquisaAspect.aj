package br.ufpe.cin.in980.linhapesquisa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.MembroDAO;
import br.ufpe.cin.in980.publicacao.Publicacao;

public privileged aspect LinhaPesquisaAspect {

	private List<LinhaPesquisa> Membro.linhasPesquisa;

	public List<LinhaPesquisa> Membro.getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void Membro.setLinhasPesquisa(List<LinhaPesquisa> linhas) {
		this.linhasPesquisa = linhas;
	}

	private List<LinhaPesquisa> Publicacao.linhasPesquisaPub;

	public List<LinhaPesquisa> Publicacao.getLinhasPesquisa() {
		return linhasPesquisaPub;
	}

	public void Publicacao.setLinhasPesquisa(List<LinhaPesquisa> linhas) {
		this.linhasPesquisaPub = linhas;
	}

	public void Fachada.cadastrarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		if (linhaPesquisa == null) {
			throw new Exception("Linha de pesquisa invalida");
		}
		this.conexao.createConnection();
		ControleLinhaPesquisa controleLinhaPesquisa = new ControleLinhaPesquisa(
				this.conexao);
		controleLinhaPesquisa.cadastrarLinhaPesquisa(linhaPesquisa);
		this.conexao.commitTransaction();
		this.conexao.closeConnection();
	}

	public List<LinhaPesquisa> Fachada.buscarLinhasPesquisa(String termo)
			throws Exception {
		if (termo == null) {
			throw new Exception("Termo invalido!");
		}
		this.conexao.createConnection();
		List<LinhaPesquisa> retorno = null;
		ControleLinhaPesquisa controleLinhaPesquisa = new ControleLinhaPesquisa(
				this.conexao);
		retorno = controleLinhaPesquisa.buscarLinhasPesquisa(termo);
		this.conexao.closeConnection();
		return retorno;
	}

	public void Fachada.editarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		if (linhaPesquisa == null) {
			throw new Exception("Linha de pesquisa invalida!");
		}
		this.conexao.createConnection();
		ControleLinhaPesquisa controleLinhaPesquisa = new ControleLinhaPesquisa(
				this.conexao);
		controleLinhaPesquisa.editarLinhaPesquisa(linhaPesquisa);
		this.conexao.commitTransaction();
		this.conexao.closeConnection();
	}

	public void Fachada.deletarLinhaPesquisa(LinhaPesquisa linhaPesquisa)
			throws Exception {
		if (linhaPesquisa == null) {
			throw new Exception("Linha de pesquisa invalida!");
		}
		this.conexao.createConnection();
		ControleLinhaPesquisa controleLinhaPesquisa = new ControleLinhaPesquisa(
				this.conexao);
		controleLinhaPesquisa.deletarLinhaPesquisa(linhaPesquisa);
		this.conexao.commitTransaction();
		this.conexao.closeConnection();
	}

	pointcut adicionarLinhasPesquisaPointcut(Membro membro, MembroDAO membrodao) 
		: execution(* MembroDAO.adicionarLinhasPesquisa(..)) 
		&& args(membro) && this(membrodao);

	before(Membro membro, MembroDAO membrodao) throws Exception 
	: adicionarLinhasPesquisaPointcut(membro, membrodao) {
		PreparedStatement stat = membrodao.conexao
				.getConnection()
				.prepareStatement(
						"SELECT lp.titulo FROM linha_pesquisa lp "
								+ "JOIN membro_linhapesquisa mlp " 
								+ "ON lp.idLinhaPesquisa=mlp.idLinhaPesquisa "
								+ "WHERE mlp.idMembro = ?");
		stat.setLong(1, membro.getIdMembro());
		ResultSet tab = stat.executeQuery();
		List<LinhaPesquisa> linhas = new ArrayList<LinhaPesquisa>();
		while (tab.next()) {
			LinhaPesquisa linha = new LinhaPesquisa();
			linha.setTitulo(tab.getString(1));
			linhas.add(linha);
		}
		membro.setLinhasPesquisa(linhas);
		stat.close();
		tab.close();
	}
}