<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.linhapesquisa.LinhaPesquisa"%>
<%@page import="br.ufpe.cin.in980.fachada.Fachada"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="br.ufpe.cin.in980.publicacao.Publicacao"%>
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
					<li> <a href="cadastrar_membro.jsp"><%=prop.getCaptions().getString("cadastrarMembroKey")%></a> </li>
					<li> <a href="buscar_membro.jsp"><%=prop.getCaptions().getString("buscarMembroKey")%></a> </li>
					<li> <a href="cadastrar_publicacao.jsp"><%=prop.getCaptions().getString("cadastrarPublicacaoKey")%></a> </li>
					<li> <a href="buscar_publicacao.jsp"><%=prop.getCaptions().getString("buscarPublicacaoKey")%></a> </li>
					<li> <a href="pagina_grupo.jsp"><%=prop.getCaptions().getString("grupoKey")%></a> </li>
					<li> <a href="cadastrar_linhapesquisa.jsp"><%=prop.getCaptions().getString("cadastrarLinhaPesquisaKey")%></a> </li>
					<li class="menu-selecionado"> <a href="#"><%=prop.getCaptions().getString("buscarLinhaPesquisaKey")%></a> </li>
					<li> <a href="linhas_pesquisa.jsp"><%=prop.getCaptions().getString("listarLinhasPesquisaKey")%></a> </li>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%=prop.getCaptions().getString("buscarLinhaPesquisaKey")%></h2>
					<form action="buscar_linhapesquisa.do" method="POST">
						<table>
							<tr>
								<td> <%=prop.getCaptions().getString("tituloParteKey")%>: </td>
								<td> <input id="busca" type="text" name="termo"> </td>
								<td> <input id="buscar" type="submit" value="Buscar"> </td>
							</tr>
						</table>
					</form>
					<%
						List<LinhaPesquisa> linhaspesquisa = (List<LinhaPesquisa>) session.getAttribute("linhaspesquisa");
												if (linhaspesquisa != null) {
					%>
					<br>
					<h2> <%=prop.getCaptions().getString("resultadosKey")%> </h2>
						<%
							for (LinhaPesquisa linhaPesquisa : linhaspesquisa) { 
															String membros = "";
															for (Membro membro : linhaPesquisa.getMembros()) {
																membros += membro.getNomeMembro() + ", ";
															}  
															String publicacoes = "";
															for (Publicacao publicacao : linhaPesquisa.getPublicacoes()) {
																publicacoes += publicacao.getTitulo() + ", ";
															}
						%>
							<p> <b> id: </b> </p>
								<p> <%= linhaPesquisa.getIdLinhaPesquisa() %> </p>
							<p> <b> <%= prop.getCaptions().getString("tituloKey") %>: </b> </p>
								<p align="justify"> <%= linhaPesquisa.getTitulo() %> </p>
							<p> <b> <%= prop.getCaptions().getString("breveDescricaoKey") %>: </b> </p>
								<p align="justify"> <%= linhaPesquisa.getBreveDescricao() %> </p>
							<p> <b> <%= prop.getCaptions().getString("descricaoDetalhadaKey") %>: </b> </p>
								<p align="justify"> <%= linhaPesquisa.getDetalhadaDescricao() %> </p>
							<p> <b> <%= prop.getCaptions().getString("financiadoresKey") %>: </b> </p>
								<p align="justify"> <%= linhaPesquisa.getFinanciadores() %> </p>
							<p> <b> <%= prop.getCaptions().getString("linksRelacionadosKey") %>: </b> </p>
								<p align="justify"> <%= linhaPesquisa.getLinksRelacionados() %> </p>
							<p> <b> <%= prop.getCaptions().getString("autoresMembrosKey") %>: </b> </p>
								<p align="justify"> <%= membros %> </p>
							<p> <b> <%= prop.getCaptions().getString("publicacoesKey") %>: </b> </p>
								<p align="justify" > <%= publicacoes %> </p>
							<p> <b> <%= prop.getCaptions().getString("editarKey") %>: </b> </p>
								<p align="justify" > <a href="editar_linhapesquisa.jsp?idlinhapesquisa=<%= linhaPesquisa.getIdLinhaPesquisa() %>"> <img border="0" alt="editar" src="img/edit.gif"> </a> </p>
							<p> <b> <%= prop.getCaptions().getString("removerKey") %>: </b> </p>
								<p align="justify"> <a href="remover_linhapesquisa.do?idlinhapesquisa=<%= linhaPesquisa.getIdLinhaPesquisa() %>"> <img border="0" alt="remover" src="img/delete.jpg"> </a> </p>
						<hr>
						<% 
							} 
						%>
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