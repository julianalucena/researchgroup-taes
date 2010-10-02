package br.ufpe.cin.in980.membro;

import java.util.List;

import br.ufpe.cin.in980.publicacao.Publicacao;

public class Estudante extends Membro {

	private static final long serialVersionUID = -7266925742642456671L;

	private String nomeOrientador;
	private String nomeCoorientador;
	private TipoEstudante tipoEstudante;

	public Estudante(Long idMember, String nomeMember, String departamento,
			String universidade, String email, String telefone, String website,
			String cidade, String pais, byte[] foto, String status,
			List<Publicacao> publicacoes, String nomeOrientador,
			String nomeCoorientador, TipoEstudante tipoEstudante) {
		super(idMember, nomeMember, departamento, universidade, email,
				telefone, website, cidade, pais, foto, status, publicacoes);
		this.nomeOrientador = nomeOrientador;
		this.nomeCoorientador = nomeCoorientador;
		this.tipoEstudante = tipoEstudante;
	}

	public Estudante() {
	}

	public String getNomeOrientador() {
		return nomeOrientador;
	}

	public void setNomeOrientador(String nomeOrientador) {
		this.nomeOrientador = nomeOrientador;
	}

	public String getNomeCoorientador() {
		return nomeCoorientador;
	}

	public void setNomeCoorientador(String nomeCoorientador) {
		this.nomeCoorientador = nomeCoorientador;
	}

	public TipoEstudante getTipoEstudante() {
		return tipoEstudante;
	}

	public void setTipoEstudante(TipoEstudante tipoEstudante) {
		this.tipoEstudante = tipoEstudante;
	}

	public enum TipoEstudante {
		DOUTORADO, MESTRADO, IC
	}
}
