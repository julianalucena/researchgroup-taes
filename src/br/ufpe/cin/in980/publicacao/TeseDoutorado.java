package br.ufpe.cin.in980.publicacao;

import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;

public class TeseDoutorado extends Publicacao {

	private static final long serialVersionUID = -841150957321101607L;

	private String escola;
	private int mes;

	public TeseDoutorado(Long idPublicacao, String titulo, int ano,
			List<Membro> autoresMembros, List<NaoMembro> autoresNaoMembros,
			byte[] pdf, String escola, int mes) {
		super(idPublicacao, titulo, ano, autoresMembros, autoresNaoMembros, pdf);
		this.escola = escola;
		this.mes = mes;
	}

	public TeseDoutorado() {
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
}
