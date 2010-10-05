package br.ufpe.cin.in980.fachada;

import java.util.List;

import br.ufpe.cin.in980.membro.ControleMembro;
import br.ufpe.cin.in980.membro.ControleNaoMembro;
import br.ufpe.cin.in980.membro.Estudante;
import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;
import br.ufpe.cin.in980.membro.ProfessorPesquisador;
import br.ufpe.cin.in980.publicacao.ControlePublicacao;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.util.JDBCConnection;

public class Fachada {

	private final static Fachada INSTANCIA = new Fachada();
	private JDBCConnection conexao;

	private Fachada() {
		this.conexao = new JDBCConnection("root", "!tia*go", "localhost",
				"researchgroup");
	}

	public static Fachada obterInstancia() {
		return INSTANCIA;
	}
	
	public JDBCConnection getConexao() {
		return conexao;
	}

	public void cadastrarMembro(Membro membro) throws Exception {
		if (membro != null) {
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			controleMembro.cadastrarMembro(membro);
		}
	}

	public List<Membro> buscarMembro(String termo) throws Exception {
		if (termo != null) {
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			List<Membro> retorno = controleMembro.buscarMembro(termo);
			return retorno;
		}
		return null;
	}

	public void editarMembro(Membro membro) throws Exception {
		if (membro != null) {
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			controleMembro.editarMembro(membro);
		}

	}

	public void deletarMembro(Membro membro) throws Exception {
		if (membro != null) {
			ControleMembro controleMembro = new ControleMembro(this.conexao);
			controleMembro.deletarMembro(membro);
		}
	}

	public List<Membro> listarMembros() throws Exception {
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<Membro> retorno = controleMembro.listarMembros();
		return retorno;
	}

	public List<NaoMembro> listarNaoMembros() throws Exception {
		ControleNaoMembro controleNaoMembro = new ControleNaoMembro(
				this.conexao);
		List<NaoMembro> retorno = controleNaoMembro.listarNaoMembros();
		return retorno;
	}

	public void cadastrarPublicacao(Publicacao publicacao) throws Exception {
		if (publicacao != null) {
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			controlePublicacao.cadastrarPublicacao(publicacao);
		}
	}

	public List<Publicacao> buscarPublicacoes(String termo) throws Exception {
		List<Publicacao> retorno = null;
		if (termo != null) {
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			retorno = controlePublicacao.buscarPublicacoes(termo);
		}
		return retorno;
	}

	public Publicacao buscarPublicacao(Long idPublicacao) throws Exception {
		Publicacao retorno = null;
		if (idPublicacao != null) {
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			retorno = controlePublicacao.buscarPublicacao(idPublicacao);
		}
		return retorno;
	}

	public void editarPublicacao(Publicacao publicacao) throws Exception {
		if (publicacao != null) {
			ControlePublicacao controlePublicacao = new ControlePublicacao(
					this.conexao);
			controlePublicacao.editarPublicacao(publicacao);
		}
	}

	public List<ProfessorPesquisador> listarProfessores() throws Exception {
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<ProfessorPesquisador> retorno = controleMembro.listarProfessores();
		return retorno;
	}

	public List<ProfessorPesquisador> listarPesquisadores() throws Exception {
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<ProfessorPesquisador> retorno = controleMembro.listarPesquisadores();
		return retorno;
	}

	public List<Estudante> listarEstudantes() throws Exception {
		ControleMembro controleMembro = new ControleMembro(this.conexao);
		List<Estudante> retorno = controleMembro.listarEstudantes();
		return retorno;
	}
}