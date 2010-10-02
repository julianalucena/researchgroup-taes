package br.ufpe.cin.in980.membro;

import java.io.Serializable;

public class NaoMembro implements Serializable {

	private static final long serialVersionUID = 1873728296567209305L;

	private Long idNaoMembro;
	private String nome;

	public NaoMembro(Long idNaoMembro, String nome) {
		super();
		this.idNaoMembro = idNaoMembro;
		this.nome = nome;
	}

	public NaoMembro() {
	}

	public Long getIdNaoMembro() {
		return idNaoMembro;
	}

	public void setIdNaoMembro(Long idNaoMembro) {
		this.idNaoMembro = idNaoMembro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}