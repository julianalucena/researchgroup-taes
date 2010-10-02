package br.ufpe.cin.in980.membro;

import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class ControleNaoMembro {

	private NaoMembroDAO naoMembroDAO;

	public ControleNaoMembro(JDBCConnection conexao) {
		this.naoMembroDAO = new NaoMembroDAO(conexao);
	}

	public List<NaoMembro> listarNaoMembros() throws Exception {
		return this.naoMembroDAO.listarNaoMembros();
	}
}