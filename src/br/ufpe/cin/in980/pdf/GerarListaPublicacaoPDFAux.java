package br.ufpe.cin.in980.pdf;

import java.util.List;

import br.ufpe.cin.in980.publicacao.Publicacao;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

public class GerarListaPublicacaoPDFAux {

	public void gerarListaPublicacaoPDF(List<Publicacao> publicacoes,
			Document document) {

		try {

			// PdfWriter.getInstance(document, new FileOutputStream(
			// "C://Programacao//lista_publicacao.pdf"));
			document.open();
			for (Publicacao publicacao : publicacoes) {
				// adicionando um parágrafo ao documento
				document.add(new Paragraph(publicacao.getTitulo()));
			}
		} catch (DocumentException de) {
			de.printStackTrace();
		}
		document.close();
	}
}