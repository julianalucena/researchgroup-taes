package br.ufpe.cin.in980.linhapesquisa;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufpe.cin.in980.fachada.Fachada;

public class BuscarLinhaPesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BuscarLinhaPesquisaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String termo = request.getParameter("termo");
		Fachada fachada = Fachada.obterInstancia();
		try {
			List<LinhaPesquisa> linhasPesquisa = fachada
					.buscarLinhasPesquisa(termo);
			HttpSession session = request.getSession(true);
			session.setAttribute("linhaspesquisa", linhasPesquisa);
			request.getRequestDispatcher("buscar_linhapesquisa.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}