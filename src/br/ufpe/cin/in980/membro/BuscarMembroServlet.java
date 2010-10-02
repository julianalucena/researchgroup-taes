package br.ufpe.cin.in980.membro;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SyncFailedException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufpe.cin.in980.fachada.Fachada;

public class BuscarMembroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BuscarMembroServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String termo = request.getParameter("termo");
		Fachada fachada = Fachada.obterInstancia();
		try {
			List<Membro> membros = fachada.buscarMembro(termo);
			HttpSession session = request.getSession(true);
			session.setAttribute("membros", membros);
			gerarImagem(membros);
			request.getRequestDispatcher("buscar_membro.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("falha.jsp")
					.forward(request, response);
		}
	}

	private void gerarImagem(List<Membro> membros)
			throws FileNotFoundException, IOException, SyncFailedException {
		for (Membro membro : membros) {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\Rodrigo\\Documents\\My Dropbox"
							+ "\\workspace\\researchgroup\\WebContent\\imgtemp\\"
							+ membro.getIdMembro() + ".jpg");
			fos.write(membro.getFoto());
			FileDescriptor fd = fos.getFD();
			fos.flush();
			fd.sync();
			fos.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
