package br.ufpe.cin.in980.membro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

public class DeletarMembroServlet extends HttpServletComum {

	private static final long serialVersionUID = 1L;

	public DeletarMembroServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Long idMembro = new Long(request.getParameter("idmembro"));
			Membro membro = new Membro();
			membro.setIdMembro(idMembro);
			Fachada fachada = Fachada.obterInstancia();
			fachada.deletarMembro(membro);
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}