package br.ufpe.cin.in980.util;

//#ifdef linha_pesquisa
//@
//#endif
public class AuxCompilacaoCondicional {

	public static String getAnalyticsScript(){
		String retorno = "";
		//#if use_analytics
		retorno = "<script type=\"text/javascript\">\n" + 
			"var gaJsHost = ((\"https:\" == document.location.protocol) ? \"https://ssl.\" : \"http://www.\");\n"+ 
			"document.write(unescape(\"%3Cscript src='\" + gaJsHost + \"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E\"));\n" + 
			"</script>\n" + 
			"<script type=\"text/javascript\">\n" + 
			"try{\n" + 
			"var pageTracker = _gat._getTracker(\"UA-xxxxxx-x\");\n" + 
			"pageTracker._trackPageview();\n" + 
			"} catch(err) {}\n" +  
			"</script>\n";
		//#endif
		return retorno;
	}
	
	public static String adicionarMenu() {
		String retorno = "";
		// #ifdef linha_pesquisa
//@		MessageProperties prop = new MessageProperties();
//@		retorno = "<li> <a href=\"cadastrar_linhapesquisa.jsp\">"
//@				+ prop.getCaptions().getString("cadastrarLinhaPesquisaKey")
//@				+ " </a> </li><li> <a href=\"buscar_linhapesquisa.jsp\">"
//@				+ prop.getCaptions().getString("buscarLinhaPesquisaKey")
//@				+ "</a> </li><li> <a href=\"linhas_pesquisa.jsp\">"
//@				+ prop.getCaptions().getString("listarLinhasPesquisaKey")
//@				+ "</a> </li>";
		// #endif
		return retorno;
	}
}
