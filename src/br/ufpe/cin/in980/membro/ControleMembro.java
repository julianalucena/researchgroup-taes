package br.ufpe.cin.in980.membro;

import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;
import br.ufpe.cin.in980.util.Spring;

public class ControleMembro {

	private IMembroDAO membroDAO;

	// Dependency Injection
	public void setMembroDAO(IMembroDAO membroDAO) {
		this.membroDAO = membroDAO;
	}

	public ControleMembro() {
		
	}
	
	public ControleMembro(JDBCConnection conexao) {
		this.membroDAO = (IMembroDAO) Spring.getContext().getBean("IMembroDAO");
		this.membroDAO.setConexao(conexao);
	}

	public void cadastrarMembro(Membro membro) throws Exception {
		if (isMembroInvalido(membro)) {
			throw new Exception("Informa��es do membro inv�lidas!");
		}
		this.membroDAO.cadastrarMembro(membro);
	}

	public List<Membro> buscarMembro(String termo) throws Exception {
		// if (!termo.isEmpty()) {
		return this.membroDAO.buscarMembro(termo);
		// }
		// return null;
	}

	public void editarMembro(Membro membro) throws Exception {
		if (isMembroInvalido(membro)) {
			throw new Exception("Informa��es do membro inv�lidas!");
		}
		this.membroDAO.editarMembro(membro);
	}

	public void deletarMembro(Membro membro) throws Exception {
		if (membro.getIdMembro() < 1) {
			throw new Exception("Membro inv�lido");
		}
		this.membroDAO.deletarMembro(membro);
	}

	public List<Membro> listar(TipoMembroListar tipo) throws Exception {
		return this.membroDAO.listar(tipo);
	}

	private boolean isMembroInvalido(Membro membro) {
		return membro.getCidade() == null || membro.getCidade().isEmpty()
				|| membro.getDepartamento() == null
				|| membro.getDepartamento().isEmpty()
				|| membro.getEmail() == null || membro.getEmail().isEmpty()
				|| membro.getFoto() == null || membro.getFoto().length == 0
				|| membro.getNomeMembro() == null
				|| membro.getNomeMembro().isEmpty() || membro.getPais() == null
				|| membro.getPais().isEmpty() || membro.getStatus() == null
				|| membro.getStatus().isEmpty() || membro.getTelefone() == null
				|| membro.getTelefone().isEmpty()
				|| membro.getUniversidade() == null
				|| membro.getUniversidade().isEmpty()
				|| membro.getWebsite() == null || membro.getWebsite().isEmpty();
	}

}

