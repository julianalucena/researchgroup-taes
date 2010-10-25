package br.ufpe.cin.in980.publicacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PublicacaoAOM {
	private TipoPublicacao tipoEntidade;
	private Collection<Propriedade> propriedades = new ArrayList<Propriedade>();


	public PublicacaoAOM(TipoPublicacao tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}

	public TipoPublicacao getTipoEntidade() {

		return tipoEntidade;
	}
	public void addPropriedade(Propriedade propriedade) {

		propriedades.add(propriedade);
	}

	public Collection<Propriedade> getPropriedades() {
		return Collections.unmodifiableCollection(propriedades);

	}
}
