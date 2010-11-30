package br.ufpe.cin.in980.visitante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class VisitanteDAO {

	private JDBCConnection conexao;

	String INSERT = "INSERT INTO visitante (nome, chegada, saida) VALUES (?, ?, ?)";
	String SELECT_ALL = "SELECT * FROM visitante ORDER BY chegada";

	
	public VisitanteDAO(JDBCConnection conexao) {
		this.conexao = conexao;
	}
	
	public void cadastrar(Visitante visitante) throws SQLException {
		PreparedStatement insertStat = this.conexao.getConnection().prepareStatement(INSERT);
		insertStat.setString(1, visitante.getNome());
		insertStat.setDate(2, new java.sql.Date(visitante.getDataChegada().getTime().getTime()));
		insertStat.setDate(3, new java.sql.Date(visitante.getDataSaida().getTime().getTime()));
		insertStat.executeUpdate();
	}
	
	public List<Visitante> listar() throws SQLException {
		PreparedStatement selectStat = this.conexao.getConnection().prepareStatement(SELECT_ALL);
		ResultSet resultSelect = selectStat.executeQuery();
		
		List<Visitante> retorno = new ArrayList<Visitante>();
		while(resultSelect.next()) {
			Visitante v = new Visitante();
			v.setNome(resultSelect.getString(2));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(resultSelect.getDate(3));
			v.setDataChegada(cal);
			cal.setTime(resultSelect.getDate(4));
			v.setDataSaida(cal);
			
			retorno.add(v);
		}
		
		return retorno;
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();

	    // set Date portion to January 1, 1970
	    calendar.set(Calendar.YEAR, 2010);
	    calendar.set(Calendar.MONTH, Calendar.DECEMBER);
	    calendar.set(Calendar.DATE, 1);

	    // normalize the object
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

		
		Visitante v = new Visitante("Lian", calendar, calendar);
		JDBCConnection con = new JDBCConnection("root", "004907", "com.mysql.jdbc.Driver", 
				"jdbc:mysql://localhost/researchgroup");
		con.createConnection();
		VisitanteDAO vDAO = new VisitanteDAO(con);
		try {
			//vDAO.cadastrar(v);
			List<Visitante> vs = vDAO.listar();
			for (Visitante visitante : vs) {
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.closeConnection();
	}
	
}
