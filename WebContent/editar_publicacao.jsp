<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.publicacao.Publicacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufpe.cin.in980.util.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.projetopesquisa.AuxMenuProjetoPesquisa"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
</head>
<body>
	<% 
		Long idPublicacao = new Long(request.getParameter("idpublicacao"));
		List<Publicacao> publicacoes = (ArrayList<Publicacao>) session.getAttribute("publicacoes");
		Publicacao publicacao = null;
		if (publicacoes != null) {
			for (Publicacao temp : publicacoes) {
				if (temp.getIdPublicacao().equals(idPublicacao)) {
					publicacao = temp;
				}
			}
		} else {
			request.getRequestDispatcher("falha.jsp")
			.forward(request, response);
		}
	%>
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
					<h2><%= prop.getCaptions().getString("editarPublicacaoKey") %></h2>
					<form action="editar_publicacao.do" method="post" enctype="multipart/form-data">
			 			<table>
			 				<tr>
			 					<td>ID: </td>
			 					<td> <input id="idpublicacao" name="idpublicacao" type="text" disabled="disabled" value="<%= publicacao.getIdPublicacao() %>" > </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("tituloKey") %>: </td>
			 					<td> <input id="titulo" name="titulo" type="text" value="<%= publicacao.getTitulo() %>" > </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("anoKey") %>: </td>
			 					<td> <input id="ano" name="ano" type="text" value="<%= publicacao.getAno() %>" > </td>
			 				</tr>
			 				<tr>
			 					<td>PDF: </td>
			 					<td> <input id="pdf" name="pdf" type="file"> </td>
			 				</tr>
			 				<tr>
								<td><%= prop.getCaptions().getString("tipoPublicacaoKey") %>: </td>
								<td> <select id="tipo" name="tipo">
										 <option value="" onclick="" ></option>
										 <option value="periodicorevista" ><%= prop.getCaptions().getString("periodicoRevistaKey") %></option>
										 <option value="conferencia" ><%= prop.getCaptions().getString("artigoConferenciaKey") %></option>
										 <option value="dissertacaomestrado" ><%= prop.getCaptions().getString("dissertacaoMestradoKey") %></option> 
									 	 <option value="tesedoutorado" ><%= prop.getCaptions().getString("teseDoutoradoKey") %></option>
									 </select> 
								</td>
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