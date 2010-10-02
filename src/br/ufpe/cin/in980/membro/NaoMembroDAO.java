package br.ufpe.cin.in980.membro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.in980.util.JDBCConnection;

public class NaoMembroDAO {

	private JDBCConnection conexao;

	public NaoMembroDAO(JDBCConnection conexao) {
		this.conexao = conexao;
	}

	public List<NaoMembro> listarNaoMembros() throws Exception {
		PreparedStatement stat = this.conexao.getConnection().prepareStatement(
				"SELECT * FROM naomembro");
		ResultSet tab = stat.executeQuery();
		List<NaoMembro> retorno = new ArrayList<NaoMembro>();
		while (tab.next()) {
			NaoMembro naoMembro = new NaoMembro();
			naoMembro.setIdNaoMembro(tab.getLong(1));
			naoMembro.setNome(tab.getString(2));
			retorno.add(naoMembro);
		}
		tab.close();
		stat.close();
		return retorno;
	}
}