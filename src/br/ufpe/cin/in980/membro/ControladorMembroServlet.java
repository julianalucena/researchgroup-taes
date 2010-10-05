package br.ufpe.cin.in980.membro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

/**
 * Servlet implementation class ControladorMembro
 */
public class ControladorMembroServlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorMembroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		Fachada fachada = Fachada.obterInstancia();
		String acao = "";
		List<FileItem> items = null;
		if (ServletFileUpload.isMultipartContent(request)) {
		
			try {
				items = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
			} catch (FileUploadException e1) {
	
				e1.printStackTrace();
			}
			
				for (FileItem item : items) {
					if(item.getFieldName().equals("acao")){
						acao = item.getString();					
					}
			}
		}else {					
			acao = request.getParameter("acao");
		}
				
		try{
			if(acao.equals("deletar")){
				deletarMembro(request, fachada);
			}else{
				cadastrarEditarMembro(request, response, fachada, acao, items);
			}
			request.getRequestDispatcher("sucesso.jsp").forward(request,
					response);
		}catch(Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp").forward(request, response);
		}
		
	}

	private void cadastrarEditarMembro(HttpServletRequest request, HttpServletResponse response, 
			Fachada fachada, String acao, List<FileItem> items)
			throws ServletException, IOException, Exception {
		Membro membro = null;
		MembroAux membroAux = new MembroAux();
		membro = membroAux.membroAux(request, response, items);
		if(acao.equals("cadastrar")){
			fachada.cadastrarMembro(membro);
		}else {
			fachada.editarMembro(membro);
		}
	}

	private void deletarMembro(HttpServletRequest request, Fachada fachada)
			throws Exception {
		Membro membro;
		Long idMembro = new Long(request.getParameter("idmembro"));
		membro = new Membro();
		membro.setIdMembro(idMembro);
		fachada.deletarMembro(membro);
	}

}
