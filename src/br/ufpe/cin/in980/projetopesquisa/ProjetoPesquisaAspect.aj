package br.ufpe.cin.in980.projetopesquisa;

import java.sql.PreparedStatement;
import java.util.List;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.publicacao.PublicacaoDAO;

public privileged aspect ProjetoPesquisaAspect {

	private ProjetoPesquisa Publicacao.projetoPesquisa;

	public ProjetoPesquisa Publicacao.getProjetoPesquisa() {
		return projetoPesquisa;
	}

	public void Publicacao.setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
		this.projetoPesquisa = projetoPesquisa;
	}

	public void Fachada.cadastrarProjetoPesquisa(ProjetoPesquisa projetoPesquisa)
			throws Exception {
		if (projetoPesquisa != null) {
			this.conexao.createConnection();
			ControleProjetoPesquisa controleProjetoPesquisa = new ControleProjetoPesquisa(
					this.conexao);
			controleProjetoPesquisa.cadastrarProjetoPesquisa(projetoPesquisa);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}
	}

	public void PublicacaoDAO.associarProjeto(Publicacao publicacao)
			throws Exception {
		PreparedStatement stat2 = this.conexao.getConnection()
				.prepareStatement(
						"INSERT INTO projetopesquisa_publicacao" +
						"(idProjetoPesquisa, idPublicacao) VALUES (?, ?)");
		stat2
				.setLong(1, publicacao.getProjetoPesquisa()
						.getIdProjetoPesquisa());
		stat2.setLong(2, publicacao.getIdPublicacao());
		stat2.executeUpdate();
		stat2.close();
	}
	
	public List<ProjetoPesquisa> Fachada.listarProjetosPesquisa() throws Exception {
		this.conexao.createConnection();
		ControleProjetoPesquisa controleProjetoPesquisa = new ControleProjetoPesquisa(
				this.conexao);
		List<ProjetoPesquisa> retorno = controleProjetoPesquisa
				.listarProjetosPesquisa();
		this.conexao.closeConnection();
		return retorno;
	}

	pointcut associarProjeto(Publicacao publicacao, PublicacaoDAO publicacaoDAO) 
		: execution(* PublicacaoDAO.associarProjeto(..)) 
		&& args(publicacao) && this(publicacaoDAO);

	after(Publicacao publicacao, PublicacaoDAO publicacaoDAO) throws Exception 
		: associarProjeto(publicacao, publicacaoDAO) {
		publicacaoDAO.associarProjeto(publicacao);
	}
}