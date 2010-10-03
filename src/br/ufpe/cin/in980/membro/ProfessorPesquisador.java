package br.ufpe.cin.in980.membro;

import java.util.List;

import br.ufpe.cin.in980.publicacao.Publicacao;

public class ProfessorPesquisador extends Membro {
	
	private static final long serialVersionUID = -953432387626595763L;

	private TipoVinculo tipoVinculo;

	public ProfessorPesquisador(Long idMember, String nomeMember, String departamento,
			String universidade, String email, String telefone, String website,
			String cidade, String pais, byte[] foto, String status,
			List<Publicacao> publicacoes, TipoVinculo tipoVinculo) {
		super(idMember, nomeMember, departamento, universidade, email,
				telefone, website, cidade, pais, foto, status, publicacoes);
		this.tipoVinculo = tipoVinculo;
	}

	public ProfessorPesquisador() {
	}
	
	public TipoVinculo getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(TipoVinculo tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}
	
	public enum TipoVinculo {
		PROFESSOR, PESQUISADOR
	}
	
}
