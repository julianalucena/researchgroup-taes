package br.ufpe.cin.in980.util;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Velocity {

	public void gerarJSPLinhaPesquisa() {
		try {
			/* first, get and initialize an engine */
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			/* next, get the Template */
			Template t = ve
					.getTemplate("src/br/ufpe/cin/lps/elp/base/helloworld.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
			context.put("class", "IntegerValue");
			context.put("type", "int");
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());
			File file = new File(
					"src/br/ufpe/cin/lps/elp/base/expression/IntegerValue.java");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(writer.toString());
			fileWriter.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
