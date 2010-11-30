<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.ufpe.cin.in980.visitante.Visitante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="br.ufpe.cin.in980.util.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.visitante.AuxMenuVisitante"%><html>
<%@page import="br.ufpe.cin.in980.fachada.Fachada"%><html>
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
					<%= AuxMenuVisitante.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<% session.setAttribute("acao", "listar"); %>
					<% 	
						Fachada fachada = Fachada.obterInstancia();
						List<Visitante> visitantes = (ArrayList<Visitante>)fachada.listarVisitantes();
						if (visitantes != null) { 
						SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
					%>
					<br>					
					<table border="1">
						<tr>							
							<td align="center" > <%= prop.getCaptions().getString("nomeKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("dataChegadaKey") %>: </td>
							<td align="center" > <%= prop.getCaptions().getString("dataSaidaKey") %>: </td>							
						</tr>
						<% 
							for(Visitante visitante : visitantes) { 
						%>
						<tr>
							<td align="center" > <%= visitante.getNome() %> </td>
							<td align="center" > <%= df.format(visitante.getDataChegada().getTime()) %> </td>
							<td align="center" > <%= df.format(visitante.getDataSaida().getTime()) %> </td>							
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