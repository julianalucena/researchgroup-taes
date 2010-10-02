package br.ufpe.cin.in980.publicacao;

import java.io.Serializable;
import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;

public class Publicacao implements Serializable {

	private static final long serialVersionUID = -7131164874025761908L;

	private Long idPublicacao;
	private String titulo;
	private int ano;
	private List<Membro> autoresMembros;
	private List<NaoMembro> autoresNaoMembros;
	private byte[] pdf;

	// TODO incluir linhas de pesquisa aqui

	public Publicacao(Long idPublicacao, String titulo, int ano,
			List<Membro> autoresMembros, List<NaoMembro> autoresNaoMembros,
			byte[] pdf) {
		super();
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.ano = ano;
		this.autoresMembros = autoresMembros;
		this.autoresNaoMembros = autoresNaoMembros;
		this.pdf = pdf;
	}

	public Publicacao() {
	}

	public Long getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(Long idPublicacao) {
		this.idPublicacao = idPublicacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<Membro> getAutoresMembros() {
		return autoresMembros;
	}

	public void setAutoresMembros(List<Membro> autoresMembros) {
		this.autoresMembros = autoresMembros;
	}

	public List<NaoMembro> getAutoresNaoMembros() {
		return autoresNaoMembros;
	}

	public void setAutoresNaoMembros(List<NaoMembro> autoresNaoMembros) {
		this.autoresNaoMembros = autoresNaoMembros;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public byte[] getPdf() {
		return pdf;
	}
}