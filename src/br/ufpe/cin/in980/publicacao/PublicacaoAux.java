package br.ufpe.cin.in980.publicacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//#ifdef projeto_pesquisa
import br.ufpe.cin.in980.projetopesquisa.ProjetoPesquisa;

public class PublicacaoAux {

	public Publicacao publicacaoAux(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idPublicacao = new Long(0l);
		String titulo = null;
		int ano = 0;
		String tipoPublicacao = null;
		byte[] pdf = null;
		int volume = 0;
		int numero = 0;
		int paginas = 0;
		int mes = 0;
		String journal = null;
		String conferencia = null;
		String escola = null;

		// #ifdef projeto_pesquisa
		Long idProjetoPesquisa = null;
		// #endif

		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			for (FileItem item : items) {
				if (item.getFieldName().equals("idpublicacao")) {
					idPublicacao = new Long(item.getString());
				}
				if (item.getFieldName().equals("titulo")) {
					titulo = item.getString();
				}
				if (item.getFieldName().equals("ano")) {
					ano = new Integer(item.getString());
				}
				if (item.getFieldName().equals("tipo")) {
					tipoPublicacao = item.getString();
				}
				if (item.getFieldName().equals("volume")) {
					volume = new Integer(item.getString());
				}
				if (item.getFieldName().equals("numero")) {
					numero = new Integer(item.getString());
				}
				if (item.getFieldName().equals("paginas")) {
					paginas = new Integer(item.getString());
				}
				if (item.getFieldName().equals("mes")) {
					mes = new Integer(item.getString());
				}
				if (item.getFieldName().equals("journal")) {
					journal = item.getString();
				}
				if (item.getFieldName().equals("conferencia")) {
					conferencia = item.getString();
				}
				if (item.getFieldName().equals("escola")) {
					escola = item.getString();
				}

				// #ifdef projeto_pesquisa
				if (item.getFieldName().equals("idProjetoPesquisa")) {
					idProjetoPesquisa = new Long(item.getString());
				}
				// #endif

				if (!item.isFormField()) {
					String nomePDF = item.getName();
					if (nomePDF.length() > 0) {
						if (nomePDF.endsWith(".pdf")) {
							pdf = item.get();
						} else {
							request.getRequestDispatcher("falha.jsp").forward(
									request, response);
						}
					}
				}
			}
		}
		Publicacao publicacao = null;
		if (tipoPublicacao.equals("periodicorevista")) {
			publicacao = new ArtPeriodicoRevista(idPublicacao, titulo, ano,
					null, null, pdf, journal, volume, numero, paginas);
		} else if (tipoPublicacao.equals("conferencia")) {
			publicacao = new ArtConferencia(idPublicacao, titulo, ano, null,
					null, pdf, conferencia, paginas, mes);
		} else if (tipoPublicacao.equals("dissertacaomestrado")) {
			publicacao = new DissertacaoMestrado(idPublicacao, titulo, ano,
					null, null, pdf, escola, mes);
		} else {
			publicacao = new TeseDoutorado(idPublicacao, titulo, ano, null,
					null, pdf, escola, mes);
		}
		// #ifdef projeto_pesquisa
		publicacao.setProjetoPesquisa(new ProjetoPesquisa(idProjetoPesquisa,
				"passa", "passa"));
		// #endif

		return publicacao;
	}
}
