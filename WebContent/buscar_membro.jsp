<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.swing.ImageIcon"%>
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
					<li class="menu-selecionado"> <a href="#"><%= prop.getCaptions().getString("buscarMembroKey") %></a> </li>
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
					<h2><%= prop.getCaptions().getString("buscarMembroKey") %></h2>
					<form action="buscar_membro.do" method="POST">
						<table>
							<tr>
								<td> <b> <%= prop.getCaptions().getString("nomeParteKey") %>: </b> </td>
								<td> <input id="busca" type="text" name="termo"> </td>
								<td> <input id="buscar" type="submit" value="Buscar"> </td>
							</tr>
						</table>
					</form>
					<% 	
						List<Membro> membros = (ArrayList<Membro>)session.getAttribute("membros");
						if (membros != null) { 
					%>
					<br>
					<h2> <%= prop.getCaptions().getString("resultadosKey") %> </h2>
					<table border="1">
						<tr>
							<td align="center" > id: </td>
							<td align="center" > <%= prop.getCaptions().getString("nomeKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("departamentoKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("universidadeKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("emailKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("telefoneKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("websiteKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("cidadeKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("paisKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("fotoKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("statusKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("resultadosKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("editarKey") %> </td>
							<td align="center" > <%= prop.getCaptions().getString("removerKey") %> </td>
						</tr>
						<% 
							for(Membro membro : membros) { 
						%>
						<tr>
							<td align="center" > <%= membro.getIdMembro() %> </td>
							<td align="center" > <%= membro.getNomeMembro() %> </td>
							<td align="center" > <%= membro.getDepartamento() %> </td>
							<td align="center"> <%= membro.getUniversidade() %> </td>
							<td align="center"> <%= membro.getEmail() %> </td>
							<td align="center"> <%= membro.getTelefone() %> </td>
							<td align="center"> <%= membro.getWebsite() %> </td>
							<td align="center"> <%= membro.getCidade() %> </td>
							<td align="center"> <%= membro.getPais() %> </td>
							<td align="center"> <img width="100" height="100" src="imgtemp/<%= membro.getIdMembro() + ".jpg" %>"> </td>
							<td align="center"> <%= membro.getStatus() %> </td>
							<td align="center"> <a href="pagina_membro.jsp?nomemembro=<%= membro.getNomeMembro() %>" > Página </a> </td>
							<td align="center"> <a href="editar_membro.jsp?nomemembro=<%= membro.getNomeMembro() %>" > <img border="0" alt="editar" src="img/edit.gif"> </a> </td>
							<td align="center"> <a href="deletar_membro.do?idmembro=<%= membro.getIdMembro() %>" > <img border="0" alt="remover" src="img/delete.jpg"> </a> </td>
						</tr>
						<% 
							} 
						%>
					</table>
						<% 
							} 
						%>
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