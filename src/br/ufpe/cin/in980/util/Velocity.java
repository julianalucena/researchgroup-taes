package br.ufpe.cin.in980.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class Velocity {
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

		for (String string : map.keySet()) {
			System.out.println(map.get(string));
		}
        /*  next, get the Template  */
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("attrs", map);
        context.put("harParam", true);
        context.put("$nomeClasse", "Membro");
        context.put("nomeClasseMin", "membro");
        
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
        System.out.println( writer.toString());
	}
}
