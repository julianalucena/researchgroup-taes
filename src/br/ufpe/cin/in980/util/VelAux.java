package br.ufpe.cin.in980.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class VelAux {
	public static void main(String[] args) throws ResourceNotFoundException, ParseErrorException, VelocityException, IOException {
		/*  first, get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        Template t = null;
        try {
			ve.init();
			 t = ve.getTemplate( "src/br/ufpe/cin/in980/util/vel.vm" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("idmembro", "Long");
		map.put("nome", "String");
		map.put("tipo", "String");
		map.put("tipoEstudante", "String");
		map.put("nomeorientador", "String");
		map.put("nomecoorientador", "String");
		map.put("departamento", "String");
		map.put("universidade", "String");
		map.put("email", "String");
		map.put("telefone", "String");
		map.put("website", "String");
		map.put("cidade", "String");
		map.put("pais", "String");
		map.put("foto", "byte[]");
		map.put("status", "String");

		ArrayList<String> formats = new ArrayList<String>();
		formats.add(".pdf");
		formats.add(".png");
		formats.add(".gif");
		
        /*  next, get the Template  */
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();        
        context.put("hasParam", true);
        context.put("attrs", map);
        context.put("formats", formats);
        context.put("nomeClasse", "MembroAux");
        context.put("tipo", "Membro");
        context.put("nomeClasseMin", "membroAux");
        context.put("pacote", "membro");
        
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
        FileOutputStream out = new FileOutputStream("src/br/ufpe/cin/in980/membro/MembroAux.java");
		PrintStream p = new PrintStream(out);
		p.println(writer.toString());
	}
}
