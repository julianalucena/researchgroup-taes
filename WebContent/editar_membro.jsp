<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufpe.cin.in980.membro.Estudante"%>
<%@page import="br.ufpe.cin.in980.util.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.projetopesquisa.AuxMenuProjetoPesquisa"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
</head>
<body>
    <% 
    	List<Membro> membros = (ArrayList<Membro>)session.getAttribute("membros");
    	Membro membro = null;
    	String nomeMembro = request.getParameter("nomemembro");
    	for (Membro temp : membros) {
    		if (temp.getNomeMembro().equals(nomeMembro)) {
    			membro = temp;
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
					<%= AuxCompilacaoCondicional.adicionarMenu() %>
					<%= AuxMenuProjetoPesquisa.adicionarMenu() %>
				</ul>
			</div>
		</div>
		<div id="interna">
			<div id="colunaA-interna">
				<div class="chamada-principal">
					<h2><%= prop.getCaptions().getString("editarMembroKey") %></h2>
					<form action="controlador_membro.do" method="POST" enctype="multipart/form-data" >
						<input type="hidden" name="acao" value="editar">
						<input name="idmembro" type="hidden" id="hidden" value="<%= membro.getIdMembro() %>" >
						<table id="table">
								<tr>
									<td><%= prop.getCaptions().getString("nomeKey") %>: </td>
									<td> <input id="nome" name="nome" type="text" value="<%= membro.getNomeMembro() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("tipoKey") %>: </td>
									<td> <select id="tipo" name="tipo">
											 <option value="professor"><%= prop.getCaptions().getString("professorKey") %></option>
											 <option value="pesquisador"><%= prop.getCaptions().getString("pesquisadorKey") %></option>
											 <option value="estudante"><%= prop.getCaptions().getString("estudanteKey") %></option>
										 </select> 
									</td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("departamentoKey") %>: </td>
									<td> <input id="departamento" name="departamento" type="text" value="<%= membro.getDepartamento() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("universidadeKey") %>: </td>
									<td> <input id="universidade" name="universidade" type="text" value="<%= membro.getUniversidade() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("emailKey") %>: </td>
									<td> <input id="email" name="email" type="text" value="<%= membro.getEmail() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("telefoneKey") %>: </td>
									<td> <input id="telefone" name="telefone" type="text" value="<%= membro.getTelefone() %>"> </td>
								</tr>	
								<tr>
									<td><%= prop.getCaptions().getString("websiteKey") %>: </td>
									<td> <input id="website" name="website" type="text" value="<%= membro.getWebsite() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("cidadeKey") %>: </td>
									<td> <input id="cidade" name="cidade" type="text" value="<%= membro.getCidade() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("paisKey") %>: </td>
									<td> <input id="pais" name="pais" type="text" value="<%= membro.getPais() %>"> </td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("fotoKey") %>:</td>
									<td><input id="foto" name="foto" type="file" /></td>
								</tr>
								<tr>
									<td><%= prop.getCaptions().getString("statusKey") %>: </td>
									<td> <select id="status" name="status">
											 <option value="ativo"><%= prop.getCaptions().getString("ativoKey") %></option>
											 <option value="former"><%= prop.getCaptions().getString("formerKey") %></option>
										 </select> 
									</td>
								</tr>
								<% 
									if (membro instanceof Estudante) {
										Estudante estudante = (Estudante) membro;
								%>
									<tr> 
										<td><%= prop.getCaptions().getString("nomeOrientadorKey") %>: </td> 
										<td> <input id="nomeorientador" name="nomeorientador" type="text" value="<%= estudante.getNomeOrientador() %>"> </td> 
									</tr> 
									<tr> 
										<td><%= prop.getCaptions().getString("nomeCoorientadorKey") %>: </td> 
										<td> <input id="nomecoorientador" name="nomecoorientador" type="text" value="<%= estudante.getNomeCoorientador() %>"> </td> 
									</tr> 
									<tr> 
										<td><%= prop.getCaptions().getString("statusKey") %>: </td> 
										<td><select id="tipoEstudante" name="tipoEstudante"> 
												<option value="DOUTORADO"><%= prop.getCaptions().getString("doutoradoKey") %></option> 
												<option value="MESTRADO"><%= prop.getCaptions().getString("mestradoKey") %></option> 
												<option value="IC">IC</option> 
											</select>  
										</td> 
									</tr>
								<%
									}
								%>
								<tr>
									<td><input id="submit" type="submit" value="OK" name="OK"> </td>
								</tr>
						</table>
						<div id="adicional"></div>
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