package br.ufpe.cin.in980.membro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

public class EditarMembroServlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;

	public EditarMembroServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			MembroAux membroAux = new MembroAux();
			Membro membro = membroAux.membroAux(request, response);
			Fachada fachada = Fachada.obterInstancia();
			fachada.editarMembro(membro);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}