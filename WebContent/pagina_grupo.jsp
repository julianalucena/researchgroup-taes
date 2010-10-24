<%@page import="br.ufpe.cin.in980.membro.ProfessorPesquisador"%>
<%@page import="br.ufpe.cin.in980.membro.TipoMembroListar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.ufpe.cin.in980.fachada.Fachada"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.membro.Estudante"%>
<%@page import="br.ufpe.cin.in980.membro.ProfessorPesquisador"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="br.ufpe.cin.in980.util.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.projetopesquisa.AuxMenuProjetoPesquisa"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
</head>
<body>
	<div id="area-busca">
		<div id="busca">
		</div>
	</div>
	<div id="main">
		<div id="topo">
			<h1> <a href="index.jsp"><img src="img/cin.png" alt="RGS" border="0" longdesc="index.jsp" /></a></h1>
			<div id="menu">
				<ul>
					<li> <a href="index.jsp">Home</a> </li>
					<li> <a href="cadastrar_membro.jsp"><%= prop.getCaptions().getString("cadastrarMembroKey") %></a> </li>
					<li> <a href="buscar_membro.jsp"><%= prop.getCaptions().getString("buscarMembroKey") %></a> </li>
					<li> <a href="cadastrar_publicacao.jsp"><%= prop.getCaptions().getString("cadastrarPublicacaoKey") %></a> </li>
					<li> <a href="buscar_publicacao.jsp"><%= prop.getCaptions().getString("buscarPublicacaoKey") %></a> </li>
					<li class="menu-selecionado"> <a href="#"><%= prop.getCaptions().getString("grupoKey") %></a> </li>
					<%= AuxCompilacaoCondicional.adicionarMenu() %>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%= prop.getCaptions().getString("paginaGrupoKey") %></h2>
					<% Fachada fachada = Fachada.obterInstancia(); %>
					<h3><%= prop.getCaptions().getString("professoresKey") %>: </h3>
					<%
						try {
							List<Membro> professores = fachada.listar(TipoMembroListar.PROFESSOR);
					%>
					<%
							for (Membro membro : professores) {
								ProfessorPesquisador professor = ((ProfessorPesquisador) membro);
					%>
							<table>
								<tr>
									<td> <%= prop.getCaptions().getString("nomeKey") %>: </td>
									<td> <%= professor.getNomeMembro() %> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("fotoKey") %>: </td>
									<td> <img width="100" height="100" src="imgtemp/<%= professor.getIdMembro() + ".jpg" %>"> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("departamentoKey") %>: </td>
									<td> <%= professor.getDepartamento() %> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("universidadeKey") %>: </td>
									<td> <%= professor.getUniversidade() %> </td>
								</tr>
							</table>
					<%			
							}
					%>
					<%
						} catch (Exception e) {
							request.getRequestDispatcher("falha.jsp")
							.forward(request, response);
						}
					%>
					<hr>
					
					<h3><%= prop.getCaptions().getString("pesquisadoresKey") %>: </h3>
					<%
						
						try {
							List<Membro> pesquisadores = fachada.listar(TipoMembroListar.PESQUISADOR);
					%>
						<table>
					<%
							for (Membro membro : pesquisadores) {
								ProfessorPesquisador pesquisador = ((ProfessorPesquisador) membro);
					%>
								<tr>
									<td> <%= prop.getCaptions().getString("nomeKey") %>: </td>
									<td> <%= pesquisador.getNomeMembro() %> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("fotoKey") %>: </td>
									<td> <img width="100" height="100" src="imgtemp/<%= pesquisador.getIdMembro() + ".jpg" %>"> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("departamentoKey") %>: </td>
									<td> <%= pesquisador.getDepartamento() %> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("universidadeKey") %>: </td>
									<td> <%= pesquisador.getUniversidade() %> </td>
								</tr>
					<%			
							}
					%>
						</table>
					<%
						} catch (Exception e) {
							request.getRequestDispatcher("falha.jsp")
							.forward(request, response);
						}
					%>
					<hr>
					
					<h3><%= prop.getCaptions().getString("estudantesKey") %>: </h3>
					<%
						
						try {
							List<Membro> estudantes = fachada.listar(TipoMembroListar.ESTUDANTE);
					%>
						<table>
					<%
							for (Membro membro : estudantes) {
								Estudante estudante = ((Estudante) membro);
					%>
								<tr>
									<td> <%= prop.getCaptions().getString("nomeKey") %>: </td>
									<td> <%= estudante.getNomeMembro() %> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("fotoKey") %>: </td>
									<td> <img width="100" height="100" src="imgtemp/<%= estudante.getIdMembro() + ".jpg" %>"> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("departamentoKey") %>: </td>
									<td> <%= estudante.getDepartamento() %> </td>
								</tr>
								<tr>
									<td> <%= prop.getCaptions().getString("universidadeKey") %>: </td>
									<td> <%= estudante.getUniversidade() %> </td>
								</tr>
					<%			
							}
					%>
						</table>
					<%
						} catch (Exception e) {
							request.getRequestDispatcher("falha.jsp")
							.forward(request, response);
						}
					%>
					<hr>
					<div class="link">
					</div>				
				</div>
				<div id="servicos-home">
		  		</div>
			</div>
			<%@ include file="layout/contato.rsc" %>
		</div>
	</div>
	<%@ include file="layout/rodape.rsc" %>
</body>
</html>