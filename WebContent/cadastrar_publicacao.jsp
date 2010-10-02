<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.in980.membro.Membro"%>
<%@page import="br.ufpe.cin.in980.fachada.Fachada"%>
<%@page import="br.ufpe.cin.in980.membro.NaoMembro"%>
<%@page import="br.ufpe.cin.in980.util.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.projetopesquisa.AssociarProjetoAux"%>

<%@page import="br.ufpe.cin.in980.projetopesquisa.AuxMenuProjetoPesquisa"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
<script src="js/prototype.js" type="text/javascript"></script>
<script src="js/scriptaculous/scriptaculous.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
	function mostrarAdicionaisPeriodico(){
		var myAjax = new Ajax.Request(
			'add_adicional.do', 
			{
				method: 'post',
				onComplete: inserirAdicionalPeriodico
			}
		);
	}

	function inserirAdicionalPeriodico(req){
		var documentTags = req.responseXML.getElementsByTagName('document');
		var retorno;
		for(i = 0; i < documentTags.length; i++) {
			retorno = documentTags[i].getElementsByTagName('adicional')[0].firstChild.nodeValue;
		}
		var adicional = $('adicional');
		adicional.innerHTML = '<table> <tr> <td><%= prop.getCaptions().getString("volumeKey") %>:</td> <td> <input id="volume" name="volume" type="text"> </td> </tr> <tr> <td><%= prop.getCaptions().getString("numeroKey") %>:</td> <td> <input id="numero" name="numero" type="text"> </td> </tr> <tr> <td><%= prop.getCaptions().getString("paginasKey") %>:</td> <td> <input id="paginas" name="paginas" type="text"> </td> </tr> <tr> <td>Journal:</td> <td> <input id="journal" name="journal" type="text"> </td> </tr> </table>';
	}

	function mostrarAdicionaisConferencia(){
		var myAjax = new Ajax.Request(
			'add_adicional.do', 
			{
				method: 'post',
				onComplete: inserirAdicionalConferencia
			}
		);
	}

	function inserirAdicionalConferencia(req){
		var documentTags = req.responseXML.getElementsByTagName('document');
		var retorno;
		for(i = 0; i < documentTags.length; i++) {
			retorno = documentTags[i].getElementsByTagName('adicional')[0].firstChild.nodeValue;
		}
		var adicional = $('adicional');
		adicional.innerHTML = '<table> <tr> <td><%= prop.getCaptions().getString("paginasKey") %>: </td> <td> <input id="paginas" name="paginas" type="text"> </td> </tr> <tr> <td><%= prop.getCaptions().getString("mesKey") %>: </td> <td> <input id="mes" name="mes" type="text"> </td> </tr> <tr> <td><%= prop.getCaptions().getString("conferenciaKey") %>:</td> <td> <input id="conferencia" name="conferencia" type="text"> </td> </tr> </table>';
	}

	function mostrarAdicionaisMestradoDoutorado(){
		var myAjax = new Ajax.Request(
			'add_adicional.do', 
			{
				method: 'post',
				onComplete: inserirAdicionalMestradoDoutorado
			}
		);
	}

	function inserirAdicionalMestradoDoutorado(req){
		var documentTags = req.responseXML.getElementsByTagName('document');
		var retorno;
		for(i = 0; i < documentTags.length; i++) {
			retorno = documentTags[i].getElementsByTagName('adicional')[0].firstChild.nodeValue;
		}
		var adicional = $('adicional');
		adicional.innerHTML = '<table> <tr> <td> <%= prop.getCaptions().getString("mesKey") %>: </td> <td> <input id="mes" name="mes" type="text"> </td> </tr> <tr> <td><%= prop.getCaptions().getString("escolaKey") %>:</td> <td> <input id="escola" name="escola" type="text"> </td> </tr> </table>';
	}
</script>
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
					<li class="menu-selecionado"> <a href="#"><%= prop.getCaptions().getString("cadastrarPublicacaoKey") %></a> </li>
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
					<h2><%= prop.getCaptions().getString("cadastrarPublicacaoKey") %></h2>
					<form action="cadastrar_publicacao.do" method="post" enctype="multipart/form-data">
			 			<table>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("tituloKey") %>: </td>
			 					<td> <input id="titulo" name="titulo" type="text"> </td>
			 				</tr>
			 				<tr>
			 					<td><%= prop.getCaptions().getString("anoKey") %>: </td>
			 					<td> <input id="ano" name="ano" type="text"> </td>
			 				</tr>
			 				<tr>
			 					<td>PDF: </td>
			 					<td> <input id="pdf" name="pdf" type="file"> </td>
			 				</tr>
			 				<tr>
								<td><%= prop.getCaptions().getString("tipoPublicacaoKey") %>: </td>
								<td> <select id="tipo" name="tipo">
										 <option value="" onclick="" ></option>
										 <option value="periodicorevista" onclick="mostrarAdicionaisPeriodico(); return false;"><%= prop.getCaptions().getString("periodicoRevistaKey") %></option>
										 <option value="conferencia" onclick="mostrarAdicionaisConferencia(); return false;"><%= prop.getCaptions().getString("artigoConferenciaKey") %></option>
										 <option value="dissertacaomestrado" onclick="mostrarAdicionaisMestradoDoutorado(); return false;"><%= prop.getCaptions().getString("dissertacaoMestradoKey") %></option> 
									 	 <option value="tesedoutorado" onclick="mostrarAdicionaisMestradoDoutorado(); return false;"><%= prop.getCaptions().getString("teseDoutoradoKey") %></option>
									 </select>
								</td>
							</tr>
							
							<%= AssociarProjetoAux.comboBox() %>
							
							<tr> 
								<td> <input id="submit" name="submit" value="OK" type="submit" > </td>
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