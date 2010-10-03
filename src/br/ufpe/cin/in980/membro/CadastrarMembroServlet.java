package br.ufpe.cin.in980.membro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

public class CadastrarMembroServlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;

	public CadastrarMembroServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MembroAux membroAux = new MembroAux();
		Membro membro = membroAux.membroAux(request, response);
		Fachada fachada = Fachada.obterInstancia();
		try {
			fachada.cadastrarMembro(membro);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}