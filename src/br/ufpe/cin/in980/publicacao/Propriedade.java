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

	public void setTipoPropriedade(TipoPropriedade tipoPropriedade) {
		this.tipoPropriedade = tipoPropriedade;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
}
