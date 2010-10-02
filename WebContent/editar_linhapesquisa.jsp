<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.basic.Membro"%>
<%@page import="br.ufpe.cin.in980.logic.Fachada"%>
<%@page import="br.ufpe.cin.in980.basic.Publicacao"%>
<%@page import="br.ufpe.cin.in980.linhapesquisa.LinhaPesquisa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufpe.cin.in980.view.AuxCompilacaoCondicional"%>

<%@page import="br.ufpe.cin.in980.view.AuxMenuProjetoPesquisa"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
</head>
<body>
	<%
		List<LinhaPesquisa> linhaspesquisa = (ArrayList<LinhaPesquisa>)session.getAttribute("linhaspesquisa");
		    	LinhaPesquisa linhaPesquisa = null;
		    	Long idLinhaPesquisa = new Long(request.getParameter("idlinhapesquisa"));
		    	for (LinhaPesquisa temp : linhaspesquisa) {
		    		if (temp.getIdLinhaPesquisa().equals(idLinhaPesquisa)) {
		    			linhaPesquisa = temp;
		    			break;
		    		}
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
					<li> <a href="cadastrar_linhapesquisa.jsp"><%= prop.getCaptions().getString("cadastrarLinhaPesquisaKey") %></a> </li>
					<li> <a href="buscar_linhapesquisa.jsp"><%= prop.getCaptions().getString("buscarLinhaPesquisaKey") %></a> </li>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%= prop.getCaptions().getString("editarLinhaPesquisaKey") %></h2>
					<form action="editar_linhapesquisa.do" method="post">
						<input type="hidden" name="idLinhaPesquisa" value="<%= linhaPesquisa.getIdLinhaPesquisa() %>" >
			 			<table>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("tituloKey") %>: </td>
			 					<td> <input id="titulo" name="titulo" type="text" value="<%= linhaPesquisa.getTitulo() %>" readonly="readonly" > </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("breveDescricaoKey") %>: </td>
			 					<td> <input id="breve_descricao" name="breve_descricao" type="text" value="<%= linhaPesquisa.getBreveDescricao() %>" > </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("descricaoDetalhadaKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="detalhada_descricao" name="detalhada_descricao" > <%= linhaPesquisa.getDetalhadaDescricao() %> </textarea> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("financiadoresKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="financiadores" name="financiadores" > <%= linhaPesquisa.getFinanciadores() %> </textarea> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("linksRelacionadosKey") %>: </td>
			 					<td> <textarea rows="5" cols="70" id="links_relacionados" name="links_relacionados"> <%= linhaPesquisa.getLinksRelacionados() %> </textarea> </td>
			 				</tr>
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
								<td><%= prop.getCaptions().getString("publicacoesKey") %>: </td>
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
										 <td> <input type="checkbox" name="publicacoes" value="<%= publicacao.getIdPublicacao() %>"> <%= publicacao.getTitulo() %> </td>
									<% 
										} 
									%>
								<% 
									} 
								%>
							</tr>
							<tr> 
								<td> <input id="submit" name="submit" value="OK" type="submit"> </td>
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