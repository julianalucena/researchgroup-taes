package br.ufpe.cin.in980.publicacao;

import java.util.HashMap;
import java.util.Map;

public class TipoEntidade {
	
	private String nomeTipo;
	private Map<String, TipoPropriedade> tiposPropriedade = new HashMap<String, TipoPropriedade>();
	
	private Map<String, Operacao> operacoes = new HashMap<String, TipoPropriedade>();
	
	
	public TipoEntidade(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	
	public Map<String, TipoPropriedade> getTiposPropriedade() {
		return tiposPropriedade;
	}

	public Map<String, Operacao> getOperacoes() {
		return operacoes;
	}
	
	

}
