package br.ufpe.cin.in980.visitante;

import java.sql.SQLException;
import java.util.List;

import br.ufpe.cin.in980.fachada.Fachada;

public privileged aspect VisitanteAspecto {

	public void Fachada.cadastrarVisitante(Visitante v)	throws Exception {
		if (v != null) {
			ControleVisitante controleVisitante = new ControleVisitante(this.conexao);
			controleVisitante.cadastrar(v);
		}
	}
	
	public List<Visitante> Fachada.listarVisitantes() throws SQLException  {
		ControleVisitante controleVisitante = new ControleVisitante(this.conexao);
		return controleVisitante.listar();
	}

}
