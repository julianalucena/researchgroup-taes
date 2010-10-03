package br.ufpe.cin.in980.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdicionaisAjaxServlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;

	public AdicionaisAjaxServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StringBuffer xml = new StringBuffer();

		xml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		xml.append("<document>");
		xml.append("<adicional>");
		xml.append("conferido");
		xml.append("</adicional>");
		xml.append("</document>");

		response.setContentType("application/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();

		out.println(xml.toString());
		out.close();
	}
}
