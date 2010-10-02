package br.ufpe.cin.in980.membro;

import java.util.List;

import br.ufpe.cin.in980.publicacao.Publicacao;

public class Pesquisador extends Membro {

	private static final long serialVersionUID = -953432387626595763L;

	public Pesquisador(Long idMember, String nomeMember, String departamento,
			String universidade, String email, String telefone, String website,
			String cidade, String pais, byte[] foto, String status,
			List<Publicacao> publicacoes) {
		super(idMember, nomeMember, departamento, universidade, email,
				telefone, website, cidade, pais, foto, status, publicacoes);
	}

	public Pesquisador() {
	}
}
