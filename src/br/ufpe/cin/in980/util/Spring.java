package br.ufpe.cin.in980.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Spring {

	private static ApplicationContext ctx;
	
	public static ApplicationContext getContext() {
		if (ctx == null) {
			ctx = new FileSystemXmlApplicationContext("file:/Users/julianalucena/Desktop/taes/workspace/researchgroup/WebContent/WEB-INF/applicationContext.xml");
		}
		
		return ctx;
	}
	
}
