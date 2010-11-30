package br.ufpe.cin.in980.visitante;

import java.sql.SQLException;
import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class ControleVisitante {

	VisitanteDAO visitanteDAO;
	
	
	public ControleVisitante(JDBCConnection conexao) {
		this.visitanteDAO = new VisitanteDAO(conexao);
	}
	
	public void cadastrar(Visitante v) throws Exception {
		if (isVisitanteInvalido(v)) {
			throw new Exception("Informações do visitante são inválidas!");
		}
		
		this.visitanteDAO.cadastrar(v);
	}
	
	public List<Visitante> listar() throws SQLException {
		return this.visitanteDAO.listar();
	}
	
	private boolean isVisitanteInvalido(Visitante v) {
	
		return v.getNome() == null || v.getDataChegada() == null || v.getDataSaida() == null
			|| (v.getDataChegada().after(v.getDataSaida()));
	
	}
	
}
