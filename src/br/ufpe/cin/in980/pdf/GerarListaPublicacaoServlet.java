package br.ufpe.cin.in980.pdf;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.util.HttpServletComum;

//#ifdef lista_pdf
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
//#else
//@import javax.servlet.http.HttpSession;
//#endif
public class GerarListaPublicacaoServlet extends HttpServletComum {

	private static final long serialVersionUID = 1L;

	public GerarListaPublicacaoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Fachada fachada = Fachada.obterInstancia();
		try {
			List<Publicacao> publicacoes = fachada.buscarPublicacoes("");

			// #ifdef lista_pdf
			 response.setContentType("application/pdf");
			 Document document = new Document();
			 PdfWriter.getInstance(document, response.getOutputStream());
			 new GerarListaPublicacaoPDFAux().gerarListaPublicacaoPDF(
			 publicacoes, document);
			// #else
//@			HttpSession session = request.getSession(true);
//@			StringBuffer bib = new StringBuffer();
//@			for (Publicacao publicacao : publicacoes) {
//@				new GerarListaPublicacaoAux().gerarPublicacaoBibTex(publicacao,
//@						bib);
//@			}
//@			session.setAttribute("bib", bib.toString());
//@			request.getRequestDispatcher("bibtex.jsp").forward(request,
//@					response);
			// #endif
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}
}
