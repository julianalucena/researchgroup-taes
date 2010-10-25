<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.publicacao.Publicacao"%>
<%@page import="br.ufpe.cin.in980.publicacao.PublicacaoAOM"%>
<%@page import="br.ufpe.cin.in980.publicacao.TipoPropriedade"%>
<%@page import="br.ufpe.cin.in980.publicacao.Propriedade"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="br.ufpe.cin.in980.membro.NaoMembro"%>
<%@page import="java.util.ArrayList"%>
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
					<li class="menu-selecionado"> <a href="#"><%= prop.getCaptions().getString("buscarPublicacaoKey") %></a> </li>
					<li> <a href="pagina_grupo.jsp"><%= prop.getCaptions().getString("grupoKey") %></a> </li>
					<%= AuxCompilacaoCondicional.adicionarMenu() %>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%= prop.getCaptions().getString("buscarPublicacaoKey") %></h2>
					<form action="buscar_publicacao.do" method="POST">
						<table>
							<tr>
								<td> <%= prop.getCaptions().getString("tituloParteKey") %>: </td>
								<td> <input id="busca" type="text" name="termo"> </td>
								<td> <input id="buscar" type="submit" value="Buscar"> </td>
								<td> <a href="gerar_lista_publicacao.do">Gerar lista de publicações</a> </td>
							</tr>
						</table>
					</form>
					<% 	
						List<PublicacaoAOM> publicacoes = (ArrayList<PublicacaoAOM>) session.getAttribute("publicacoes");
						if (publicacoes != null) { 
					%>
					<br>
					<h2> <%= prop.getCaptions().getString("resultadosKey") %> </h2>
					<table border="1">
						<tr>
							<% for (TipoPropriedade tipoPropriedade : publicacoes.get(0).getTipoEntidade().getTiposPropriedade()) { %>
								<td align="center" > <%= tipoPropriedade.getNomePropriedade() %> </td>
							<% } %>
						</tr>

						<% for (PublicacaoAOM publicacao : publicacoes) { %>
							<tr>
							<% for (Propriedade propriedade : publicacao.getPropriedades()) { %>
								<td align="center" > <%= propriedade.getValor() %> </td>
							<% } %>

						</tr>
						<% } %>
					</table>
					<% } %>
			</div>
					<div class="link">
					</div>				
		</div>
			<div id="servicos-home">
	  		</div>
		</div>
			<%@ include file="layout/contato.rsc" %>
	</div>
	<%@ include file="layout/rodape.rsc" %>
</body>
</html>