package br.ufpe.cin.in980.membro;

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



public class MembroAux {

	public Membro membroAux(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long idMembro = new Long(0l);
		String nome = null;
		String tipo = null;
		String tipoEstudante = null;
		String nomeOrientador = null;
		String nomeCoorientador = null;
		String departamento = null;
		String universidade = null;
		String email = null;
		String telefone = null;
		String website = null;
		String cidade = null;
		String pais = null;
		byte[] foto = null;
		String status = null;

		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
				return null;
			}
			for (FileItem item : items) {
				if (item.getFieldName().equals("idmembro")) {
					idMembro = new Long(item.getString());
				}
				if (item.getFieldName().equals("nome")) {
					nome = item.getString();
				}
				if (item.getFieldName().equals("tipo")) {
					tipo = item.getString();
				}
				if (item.getFieldName().equals("tipoEstudante")) {
					tipoEstudante = item.getString();
				}
				if (item.getFieldName().equals("nomeorientador")) {
					nomeOrientador = item.getString();
				}
				if (item.getFieldName().equals("nomecoorientador")) {
					nomeCoorientador = item.getString();
				}
				if (item.getFieldName().equals("departamento")) {
					departamento = item.getString();
				}
				if (item.getFieldName().equals("universidade")) {
					universidade = item.getString();
				}
				if (item.getFieldName().equals("email")) {
					email = item.getString();
				}
				if (item.getFieldName().equals("telefone")) {
					telefone = item.getString();
				}
				if (item.getFieldName().equals("website")) {
					website = item.getString();
				}
				if (item.getFieldName().equals("cidade")) {
					cidade = item.getString();
				}
				if (item.getFieldName().equals("pais")) {
					pais = item.getString();
				}
				if (item.getFieldName().equals("status")) {
					status = item.getString();
				}
				if (!item.isFormField()) {
					String nomeFoto = item.getName();
					if (nomeFoto.length() > 0) {
						if (nomeFoto.endsWith(".jpg")
								|| nomeFoto.endsWith(".png")
								|| nomeFoto.endsWith(".gif")) {
							foto = item.get();
						} else {
							request.getRequestDispatcher("falha.jsp").forward(
									request, response);
						}
					}
				}
			}
		}
		Membro membro = null;
		if (tipo.equals("pesquisador")) {
			membro = new Pesquisador(idMembro, nome, departamento,
					universidade, email, telefone, website, cidade, pais, foto,
					status, null);
		} else if (tipo.equals("professor")) {
			membro = new Professor(idMembro, nome, departamento, universidade,
					email, telefone, website, cidade, pais, foto, status, null);
		} else {
			membro = new Estudante(idMembro, nome, departamento, universidade,
					email, telefone, website, cidade, pais, foto, status, null,
					nomeOrientador, nomeCoorientador, Estudante.TipoEstudante
							.valueOf(tipoEstudante));
		}
		return membro;
	}
}
