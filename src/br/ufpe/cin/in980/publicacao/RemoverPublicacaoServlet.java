package br.ufpe.cin.in980.publicacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoverPublicacaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RemoverPublicacaoServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}