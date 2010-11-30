package br.ufpe.cin.in980.visitante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import br.ufpe.cin.in980.fachada.Fachada;
import br.ufpe.cin.in980.util.HttpServletComum;

/**
 * Servlet implementation class VisitanteServlet
 */
public class VisitanteServlet extends HttpServletComum {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getSession().getAttribute("acao").toString();
		Visitante v = new Visitante();
		List<Visitante> visitantes = new ArrayList<Visitante>();
		Fachada fachada = Fachada.obterInstancia();
		
		if (acao.equals("listar")){
			try {
				visitantes = fachada.listarVisitantes();
				HttpSession session = request.getSession(true);
				session.setAttribute("visitantes", visitantes);
				request.getRequestDispatcher("listar_visitantes.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("falha.jsp").forward(request,
						response);
			}
				
		}else{
			
			String nome = request.getParameter("nome");
			String dataChegada = request.getParameter("chegada");
			String dataSaida = request.getParameter("saida");
			String[] chegada = dataChegada.split("/");
			String[] saida = dataSaida.split("/");
			
			v.setNome(nome);
			
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(chegada[2]), Integer.parseInt(chegada[1]), 
					Integer.parseInt(chegada[0]));
			v.setDataChegada(cal);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Integer.parseInt(saida[2]), Integer.parseInt(saida[1]), 
					Integer.parseInt(saida[0]));
			v.setDataSaida(cal2);

			try {
				fachada.cadastrarVisitante(v);
				request.getRequestDispatcher("sucesso.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("falha.jsp").forward(request,
						response);
			}
			
		}
			
			
		
		
		
	}

}
