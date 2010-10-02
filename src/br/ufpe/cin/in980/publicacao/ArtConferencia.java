package br.ufpe.cin.in980.publicacao;

import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;

public class ArtConferencia extends Publicacao {

	private static final long serialVersionUID = -1567677764012987496L;

	private String conferencia;
	private int paginas;
	private int mes;

	public ArtConferencia(Long idPublicacao, String titulo, int ano,
			List<Membro> autoresMembros, List<NaoMembro> autoresNaoMembros,
			byte[] pdf, String conferencia, int paginas, int mes) {
		super(idPublicacao, titulo, ano, autoresMembros, autoresNaoMembros, pdf);
		this.conferencia = conferencia;
		this.paginas = paginas;
		this.mes = mes;
	}

	public ArtConferencia() {
	}

	public String getConferencia() {
		return conferencia;
	}

	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
}