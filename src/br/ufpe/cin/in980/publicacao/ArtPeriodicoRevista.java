package br.ufpe.cin.in980.publicacao;

import java.util.List;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;

public class ArtPeriodicoRevista extends Publicacao {

	private static final long serialVersionUID = 1139586604563374873L;

	private String journal;
	private int volume;
	private int numero;
	private int paginas;

	public ArtPeriodicoRevista(Long idPublicacao, String titulo, int ano,
			List<Membro> autoresMembros, List<NaoMembro> autoresNaoMembros,
			byte[] pdf, String journal, int volume, int numero, int paginas) {
		super(idPublicacao, titulo, ano, autoresMembros, autoresNaoMembros, pdf);
		this.journal = journal;
		this.volume = volume;
		this.numero = numero;
		this.paginas = paginas;
	}

	public ArtPeriodicoRevista() {
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
}