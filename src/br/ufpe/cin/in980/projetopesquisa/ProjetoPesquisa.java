package br.ufpe.cin.in980.projetopesquisa;

public class ProjetoPesquisa {

	private Long idProjetoPesquisa;
	private String nomeProjeto;
	private String descricaoProjeto;

	public ProjetoPesquisa(Long idProjetoPesquisa, String nomeProjeto,
			String descricaoProjeto) {
		super();
		this.setIdProjetoPesquisa(idProjetoPesquisa);
		this.setNomeProjeto(nomeProjeto);
		this.setDescricaoProjeto(descricaoProjeto);
	}

	public ProjetoPesquisa() {
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setDescricaoProjeto(String descricaoProjeto) {
		this.descricaoProjeto = descricaoProjeto;
	}

	public String getDescricaoProjeto() {
		return descricaoProjeto;
	}

	public void setIdProjetoPesquisa(Long idProjetoPesquisa) {
		this.idProjetoPesquisa = idProjetoPesquisa;
	}

	public Long getIdProjetoPesquisa() {
		return idProjetoPesquisa;
	}
}
