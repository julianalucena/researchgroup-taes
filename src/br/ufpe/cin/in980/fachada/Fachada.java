package br.ufpe.cin.in980.fachada;

import java.util.List;

import br.ufpe.cin.in980.membro.ControleMembro;
import br.ufpe.cin.in980.membro.ControleNaoMembro;
import br.ufpe.cin.in980.membro.Estudante;
import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;
import br.ufpe.cin.in980.membro.Pesquisador;
import br.ufpe.cin.in980.membro.Professor;
import br.ufpe.cin.in980.publicacao.ControlePublicacao;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.util.JDBCConnection;

public class Fachada {

	private final static Fachada INSTANCIA = new Fachada();
	private JDBCConnection conexao;

	private Fachada() {
		this.conexao = new JDBCConnection("root", "004907", "localhost",
				"researchgroup");
	}

	public static Fachada obterInstancia() {
		return INSTANCIA;
	}

	public void cadastrarMembro(Membro membro) throws Exception {
		if (membro != null) {
			this.conexao.createConnection();
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			controleMembro.cadastrarMembro(membro);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}
	}

	public List<Membro> buscarMembro(String termo) throws Exception {
		if (termo != null) {
			this.conexao.createConnection();
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			List<Membro> retorno = controleMembro.buscarMembro(termo);
			this.conexao.closeConnection();
			return retorno;
		}
		return null;
	}

	public void editarMembro(Membro membro) throws Exception {
		if (membro != null) {
			this.conexao.createConnection();
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			controleMembro.editarMembro(membro);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}

	}

	public void deletarMembro(Membro membro) throws Exception {
		if (membro != null) {
			this.conexao.createConnection();
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			controleMembro.deletarMembro(membro);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}
	}

	public List<Membro> listarMembros() throws Exception {
		this.conexao.createConnection();
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<Membro> retorno = controleMembro.listarMembros();
		this.conexao.closeConnection();
		return retorno;
	}

	public List<NaoMembro> listarNaoMembros() throws Exception {
		this.conexao.createConnection();
		ControleNaoMembro controleNaoMembro = new ControleNaoMembro(
				this.conexao);
		List<NaoMembro> retorno = controleNaoMembro.listarNaoMembros();
		this.conexao.closeConnection();
		return retorno;
	}

	public void cadastrarPublicacao(Publicacao publicacao) throws Exception {
		if (publicacao != null) {
			this.conexao.createConnection();
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			controlePublicacao.cadastrarPublicacao(publicacao);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}
	}

	public List<Publicacao> buscarPublicacoes(String termo) throws Exception {
		List<Publicacao> retorno = null;
		if (termo != null) {
			this.conexao.createConnection();
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			retorno = controlePublicacao.buscarPublicacoes(termo);
			this.conexao.closeConnection();
		}
		return retorno;
	}

	public Publicacao buscarPublicacao(Long idPublicacao) throws Exception {
		Publicacao retorno = null;
		if (idPublicacao != null) {
			this.conexao.createConnection();
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			retorno = controlePublicacao.buscarPublicacao(idPublicacao);
			this.conexao.closeConnection();
		}
		return retorno;
	}

	public void editarPublicacao(Publicacao publicacao) throws Exception {
		if (publicacao != null) {
			this.conexao.createConnection();
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			controlePublicacao.editarPublicacao(publicacao);
			this.conexao.commitTransaction();
			this.conexao.closeConnection();
		}
	}

	public List<Professor> listarProfessores() throws Exception {
		this.conexao.createConnection();
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<Professor> retorno = controleMembro.listarProfessores();
		this.conexao.closeConnection();
		return retorno;
	}

	public List<Pesquisador> listarPesquisadores() throws Exception {
		this.conexao.createConnection();
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<Pesquisador> retorno = controleMembro.listarPesquisadores();
		this.conexao.closeConnection();
		return retorno;
	}

	public List<Estudante> listarEstudantes() throws Exception {
		this.conexao.createConnection();
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<Estudante> retorno = controleMembro.listarEstudantes();
		this.conexao.closeConnection();
		return retorno;
	}
}