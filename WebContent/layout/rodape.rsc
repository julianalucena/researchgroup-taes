<div id="rodape">
	<ul>
		<li> <a href="index.jsp">Home</a> </li>
		<li> <a href="cadastrar_membro.jsp"><%= prop.getCaptions().getString("cadastrarMembroKey") %></a> </li>
		<li> <a href="buscar_membro.jsp"><%= prop.getCaptions().getString("buscarMembroKey") %></a> </li>
		<li> <a href="cadastrar_publicacao.jsp"><%= prop.getCaptions().getString("cadastrarPublicacaoKey") %></a> </li>
		<li> <a href="buscar_publicacao.jsp"><%= prop.getCaptions().getString("buscarPublicacaoKey") %></a> </li>
		<li> <a href="pagina_grupo.jsp"><%= prop.getCaptions().getString("grupoKey") %></a> </li>	
		<%= AuxCompilacaoCondicional.adicionarMenu() %>				
	</ul>
	<div class="copyright">Rodrigo Cardoso - rcaa2 @ cin.ufpe.br</div>
</div>