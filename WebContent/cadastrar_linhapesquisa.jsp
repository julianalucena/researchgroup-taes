<%@page import="br.ufpe.cin.in980.membro.TipoMembroListar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="br.ufpe.cin.in980.fachada.Fachada"%>
<%@page import="br.ufpe.cin.in980.publicacao.Publicacao"%>
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
					<li> <a href="pagina_grupo.jsp"><%= prop.getCaptions().getString("grupoKey") %></a> </li>
					<li class="menu-selecionado"> <a href="#"><%= prop.getCaptions().getString("cadastrarLinhaPesquisaKey") %></a> </li>
					<li> <a href="buscar_linhapesquisa.jsp"><%= prop.getCaptions().getString("buscarLinhaPesquisaKey") %></a> </li>
					<li> <a href="linhas_pesquisa.jsp"><%= prop.getCaptions().getString("listarLinhasPesquisaKey") %></a> </li>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%= prop.getCaptions().getString("cadastrarLinhaPesquisaKey") %></h2>
					<form action="cadastrar_linhapesquisa.do" method="post">
			 			<table>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("tituloKey") %>: </td>
			 					<td> <input id="titulo" name="titulo" type="text" size="70"> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("breveDescricaoKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="breve_descricao" name="breve_descricao"></textarea> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("descricaoDetalhadaKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="detalhada_descricao" name="detalhada_descricao"></textarea> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("financiadoresKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="financiadores" name="financiadores"></textarea> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("linksRelacionadosKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="links_relacionados" name="links_relacionados"></textarea> </td>
			 				</tr>
			 			</table>
								<p><%= prop.getCaptions().getString("autoresMembrosKey") %>: </p>
								<% 
			 						List<Membro> membrosautores = null;
			 						try {
			 							membrosautores = Fachada.obterInstancia().listar(TipoMembroListar.MEMBRO);
			 						} catch (Exception e) {
			 							e.printStackTrace();
			 							request.getRequestDispatcher("falha.jsp")
			 							.forward(request, response);
			 						}
			 						if (membrosautores != null) {
			 							for (Membro membro : membrosautores) {
			 					%>
										 <p> <input type="checkbox" name="membros" value="<%= membro.getIdMembro() %>"> <%= membro.getNomeMembro() %> </p>
									<% 
										} 
									%>
								<% 
									} 
								%>
								<p><%= prop.getCaptions().getString("publicacoesKey") %>: </p>
								<% 
			 						List<Publicacao> publicacoes = null;
			 						try {
			 							publicacoes = Fachada.obterInstancia().buscarPublicacoes("");
			 						} catch (Exception e) {
			 							e.printStackTrace();
			 							request.getRequestDispatcher("falha.jsp")
			 							.forward(request, response);
			 						}
			 						if (publicacoes != null) {
			 							for (Publicacao publicacao : publicacoes) {
			 					%>
										 <p> <input type="checkbox" name="publicacoes" value="<%= publicacao.getIdPublicacao() %>"> <%= publicacao.getTitulo() %> </p>
									<% 
										} 
									%>
								<% 
									} 
								%>
								<p> <input id="submit" name="submit" value="OK" type="submit"> </p>
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