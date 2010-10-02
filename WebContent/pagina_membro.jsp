<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.ufpe.cin.in980.logic.Fachada"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.basic.Membro"%>
<%@page import="br.ufpe.cin.in980.basic.Publicacao"%>
<!-- LINHAS DE PESQUISA / COMPILAÇÃO CONDICIONAL? -->
<%@page import="br.ufpe.cin.in980.linhapesquisa.LinhaPesquisa"%>
<!-- LINHAS DE PESQUISA / COMPILAÇÃO CONDICIONAL? -->

<%@page import="br.ufpe.cin.in980.view.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.view.AuxMenuProjetoPesquisa"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
</head>
<body>
	<%
		String nomeMembro = request.getParameter("nomemembro");
			Fachada fachada = Fachada.obterInstancia();
			List<Membro> membros = fachada.buscarMembro(nomeMembro);
			if (!membros.isEmpty()) {
		Membro membro = membros.get(0);
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
					<li> <a href="cadastrar_membro.jsp"><%=prop.getCaptions().getString("cadastrarMembroKey")%></a> </li>
					<li> <a href="buscar_membro.jsp"><%=prop.getCaptions().getString("buscarMembroKey")%></a> </li>
					<li> <a href="cadastrar_publicacao.jsp"><%=prop.getCaptions().getString("cadastrarPublicacaoKey")%></a> </li>
					<li> <a href="buscar_publicacao.jsp"><%=prop.getCaptions().getString("buscarPublicacaoKey")%></a> </li>
					<li> <a href="pagina_grupo.jsp"><%=prop.getCaptions().getString("grupoKey")%></a> </li>
					<%=AuxCompilacaoCondicional.adicionarMenu()%>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%=prop.getCaptions().getString("paginaMembroKey")%></h2>
					<table>
						<tr>
							<td> <%=prop.getCaptions().getString("nomeKey")%> </td>
							<td> <%=membro.getNomeMembro()%> </td>
						</tr>
						<tr>
							<td> <%=prop.getCaptions().getString("departamentoKey")%> </td>
							<td> <%=membro.getDepartamento()%> </td>
						</tr>
						<tr>
							<td> <%=prop.getCaptions().getString("universidadeKey")%>  </td>
							<td> <%=membro.getUniversidade()%> </td>
						</tr>
						<tr>
							<td> <%=prop.getCaptions().getString("publicacoesKey")%> </td>
							<td></td>
						</tr>
				<%
					for (Publicacao publicacao : membro.getPublicacoes()) {
				%>
							<tr>
								<td> <%=prop.getCaptions().getString("tituloKey")%> </td>
								<td> <%=publicacao.getTitulo()%> </td>
							</tr>
							<tr>
								<td><%=prop.getCaptions().getString("anoKey")%></td>
								<td> <%=publicacao.getAno()%> </td>
							</tr>	
							<tr>
								<td> PDF </td>
								<td> <!-- link para download do pdf --> </td>
							</tr>
							<tr>
								<td> BibTeX </td>
								<td> <!-- link para download do bibtex --> </td>
							</tr>	
				<%
						}
					%>		
				<!-- LINHAS DE PESQUISA / COMPILAÇÃO CONDICIONAL? -->
				
						<tr>
							<td> <%=prop.getCaptions().getString("linhasPesquisaKey")%> </td>
							<td></td>
						</tr>
				<%
					for (LinhaPesquisa linhaPesquisa : membro.getLinhasPesquisa()) {
				%>
							<tr>
								<td> <%= prop.getCaptions().getString("tituloKey") %> </td>
								<td> <%= linhaPesquisa.getTitulo() %> </td>
							</tr>
				<%		
						}	
				%>		
					</table>
				<%		
					}
				%>
				<!-- LINHAS DE PESQUISA / COMPILAÇÃO CONDICIONAL? -->
				
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