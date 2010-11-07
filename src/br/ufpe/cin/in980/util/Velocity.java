package br.ufpe.cin.in980.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

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
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("nome");
		list.add("tipo");
		list.add("tipoEstudante");
		list.add("nomeorientador");
		list.add("nomecoorientador");
		list.add("departamento");
		list.add("universidade");
		list.add("email");
		list.add("telefone");
		list.add("website");
		list.add("cidade");
		list.add("pais");
		list.add("status");

		
        /*  next, get the Template  */
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("nameList", list);
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
        System.out.println( writer.toString());
	}
}
