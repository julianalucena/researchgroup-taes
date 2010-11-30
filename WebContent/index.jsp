<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Properties"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="br.ufpe.cin.in980.util.AuxCompilacaoCondicional"%>
<%@page import="br.ufpe.cin.in980.projetopesquisa.AuxMenuProjetoPesquisa"%>
<%@page import="br.ufpe.cin.in980.visitante.AuxMenuVisitante"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="layout/header.rsc" %>
<%= AuxCompilacaoCondicional.getAnalyticsScript() %>
</head>
<body>
	<div id="area-busca">
		<div id="busca">
		</div>
	</div>
	 <%
		//if (lang == null) {
	%>
	<!--		<div align="center">
				<a href="index.jsp?lang=pt"><img src="img/BandeiraBrasil.gif" width="200" height="140"> </a> 
				<a href="index.jsp?lang=en"><img src="img/bandeira-eua-img.gif" width="200" height="140"> </a>
			</div> -->
	<%
		//} else {
	%>
	<div id="main">
		<div id="topo">
			<h1> <a href="index.jsp"><img src="img/cin.png" alt="RGS" border="0" longdesc="index.jsp" /></a></h1>
			<div id="menu">
				<ul>
					<li class="menu-selecionado" > <a href="#">Home</a> </li>
					<li> <a href="cadastrar_membro.jsp"><%= prop.getCaptions().getString("cadastrarMembroKey") %></a> </li>
					<li> <a href="buscar_membro.jsp"><%= prop.getCaptions().getString("buscarMembroKey") %></a> </li>
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
					<h2> <%= prop.getCaptions().getString("areasPesquisaKey") %> </h2>
					<h3> Aspect-Oriented Programming, Separation of Concerns, Refactoring </h3>
					<p align="justify" > We are defining an implementation method that separates data management, distribution, 
					and concurrency control concerns using Aspect-Oriented Programming. We are also working on patterns, 
					guidelines and techniques for applying Aspect-Oriented Programming for providing adaptability for 
					existent and novel systems. Refactorings for AspectJ are also being considered here. 
					We are also concerned to design experimental studies in order to evaluate the defined 
					methods, tecniques, and approaches.  </p>
					<br>
					<h3> Aspect-Oriented Programming, Separation of Concerns, Refactoring </h3>
					<p align="justify" > We are investigating how to model variability in a product-line architecture using Aspect-Oriented 
					Programming techniques. The model addresses both functional and non-functional requirements 
					and complies with quality standards. In particular, our approach aims at providing a method 
					to instantiate a particular product in the line with AOP techniques. Tool support will 
					also be provided to automate the process.   </p> 
					<br>
					<h3> Refactoring and Generative Programming </h3>
					<p align="justify"> We are currently developing JaTS, a Java transformation system that supports user-defined 
					program transformations. In this way, the user can define new refactorings and code generation 
					templates using a declarative language for specifying the new transformations. An extension 
					of JaTS for AspectJ is also being developed.    </p> 
					<br>
					<h3> Refactoring and Formal Methods </h3>
					<p align="justify" > Alloy is a declarative object-oriented modeling language similar to UML, yet simpler and clearer. 
					It is based on first-order logic and relational theory, and amenable for fully automatic analysis. 
					Our goals are defining and proving transformation laws for Alloy models, applying to 
					widely-adopted model transformations as used in Refactorings and MDA (Model-Driven Architecture). 
					In addition, we are investigating the mapping of these model transformations to Java 
					program transformations, assuring the adequate correspondence between abstract models and code. 
					As part of the COOP project, we are also defining algebraic laws for a Java-like language and 
					formally deriving refactorings from those laws.    </p> 
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
	<%
	//	}
	%>
</body>
</html>