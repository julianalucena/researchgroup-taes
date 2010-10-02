<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.basic.Membro"%>
<%@page import="br.ufpe.cin.in980.logic.Fachada"%>
<%@page import="br.ufpe.cin.in980.basic.NaoMembro"%>
<%@page import="br.ufpe.cin.in980.view.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.view.AuxMenuProjetoPesquisa"%><html>
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
					<li> <a href="pagina_grupo.jsp"><%= prop.getCaptions().getString("grupoKey") %></a> </li>
					<%= AuxCompilacaoCondicional.adicionarMenu() %>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<form action="editar_publicacao2.do" method="post">
						<table>
							<tr>
								<td><%= prop.getCaptions().getString("autoresMembrosKey") %>: </td>
								<% 
			 						List<Membro> membrosautores = null;
			 						try {
			 							membrosautores = Fachada.obterInstancia().listarMembros();
			 						} catch (Exception e) {
			 							e.printStackTrace();
			 							request.getRequestDispatcher("falha.jsp")
			 							.forward(request, response);
			 						}
			 						if (membrosautores != null) {
			 							for (Membro membro : membrosautores) {
			 					%>
										 <td> <input type="checkbox" name="membros" value="<%= membro.getIdMembro() %>"> <%= membro.getNomeMembro() %> </td>
									<% 
										} 
									%>
								<% 
									} 
								%>
							</tr>
							<tr>
								<td> <%= prop.getCaptions().getString("autoresNaoMembrosKey") %>: </td>
								<% 
			 						List<NaoMembro> membrosnaoautores = null;
			 						try {
			 							membrosnaoautores = Fachada.obterInstancia().listarNaoMembros();
			 						} catch (Exception e) {
			 							e.printStackTrace();
			 							request.getRequestDispatcher("falha.jsp")
			 							.forward(request, response);
			 						}
			 						if (membrosautores != null) {
			 							for (NaoMembro naoMembro : membrosnaoautores) {
			 					%>
									<td> <input type="checkbox" name="naomembros" value="<%= naoMembro.getIdNaoMembro() %>"> <%= naoMembro.getNome() %> </td>
									<% 
										} 
									%>
								<% 
									} 
								%>
							</tr>
							<tr> 
								<td> <input id="submit" name="submit" value="OK" type="submit" > </td>
							</tr>
						</table>
					</form>
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