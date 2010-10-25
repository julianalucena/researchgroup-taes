package br.ufpe.cin.in980.publicacao;

public class TipoPropriedade {
	
	private String nomePropriedade;
	private String tipo;
	
	public TipoPropriedade(String nomePropriedade, String tipo) {
		this.nomePropriedade = nomePropriedade;
		this.tipo = tipo;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public String getTipo() {
		return tipo;
	}

}
