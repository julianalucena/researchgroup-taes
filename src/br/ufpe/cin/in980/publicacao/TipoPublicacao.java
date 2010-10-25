package br.ufpe.cin.in980.publicacao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TipoPublicacao {

	private String nomeTipo;
	private Map<String, TipoPropriedade> tiposPropriedade = new HashMap<String, TipoPropriedade>();	

	public TipoPublicacao(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	public String getNomeTipo() {
		return nomeTipo;
	}

	public Collection<TipoPropriedade> getTiposPropriedade() {
		return tiposPropriedade.values();
	}

	public void addTipoPropriedade(TipoPropriedade tipoPropriedade) {
		tiposPropriedade.put(tipoPropriedade.getNomePropriedade(), tipoPropriedade);

	}

}
