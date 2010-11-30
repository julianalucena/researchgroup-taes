package br.ufpe.cin.in980.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BDProperties {
	
	private static BDProperties instance;
	Properties props;
	
	public BDProperties() {
		File file = new File("/Users/julianalucena/cin/7-periodo/taes/workspace/researchgroup/src/bd.properties");      
		props = new Properties();  
		FileInputStream fis = null;  
		try {  
		    fis = new FileInputStream(file);  
		    props.load(fis);    
		    fis.close();  
		}  
		catch (IOException ex) {  
		    System.out.println(ex.getMessage());  
		    ex.printStackTrace();  
		}  
	}
	
    public static BDProperties getInstance(){
        if  (instance == null) {
        	instance = new BDProperties();
        }
    	
    	return instance;
     }
    
    public String getProperty(String nome) {
    	return props.getProperty(nome);
    }
	
} 