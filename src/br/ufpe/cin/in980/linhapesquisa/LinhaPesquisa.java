package br.ufpe.cin.in980.linhapesquisa;

import java.io.Serializable;
import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.publicacao.Publicacao;

public class LinhaPesquisa implements Serializable {

	private static final long serialVersionUID = -225608711698259583L;

	private Long idLinhaPesquisa;
	private String titulo;
	private String breveDescricao;
	private String detalhadaDescricao;
	private String financiadores;
	private String linksRelacionados;
	private List<Membro> membros;
	private List<Publicacao> publicacoes;

	public LinhaPesquisa(Long idLinhaPesquisa, String titulo,
			String breveDescricao, String detalhadaDescricao,
			String financiadores, String linksRelacionados,
			List<Membro> membros, List<Publicacao> publicacoes) {
		super();
		this.idLinhaPesquisa = idLinhaPesquisa;
		this.titulo = titulo;
		this.breveDescricao = breveDescricao;
		this.detalhadaDescricao = detalhadaDescricao;
		this.financiadores = financiadores;
		this.linksRelacionados = linksRelacionados;
		this.membros = membros;
		this.publicacoes = publicacoes;
	}

	public LinhaPesquisa() {
	}

	public Long getIdLinhaPesquisa() {
		return idLinhaPesquisa;
	}

	public void setIdLinhaPesquisa(Long idLinhaPesquisa) {
		this.idLinhaPesquisa = idLinhaPesquisa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getBreveDescricao() {
		return breveDescricao;
	}

	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}

	public String getDetalhadaDescricao() {
		return detalhadaDescricao;
	}

	public void setDetalhadaDescricao(String detalhadaDescricao) {
		this.detalhadaDescricao = detalhadaDescricao;
	}

	public String getFinanciadores() {
		return financiadores;
	}

	public void setFinanciadores(String financiadores) {
		this.financiadores = financiadores;
	}

	public String getLinksRelacionados() {
		return linksRelacionados;
	}

	public void setLinksRelacionados(String linksRelacionados) {
		this.linksRelacionados = linksRelacionados;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
}