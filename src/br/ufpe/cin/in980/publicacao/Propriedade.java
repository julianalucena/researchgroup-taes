package br.ufpe.cin.in980.publicacao;

public class Propriedade {
	
	private TipoPropriedade tipoPropriedade;
	private Object valor;
	
	public Propriedade(TipoPropriedade tipoPropriedade, Object valor) {
		
		this.tipoPropriedade = tipoPropriedade;
		this.valor = valor;
		
	}

	public TipoPropriedade getTipoPropriedade() {
		return tipoPropriedade;
	}

	public Object getValor() {
		return valor;
	}
	
}
