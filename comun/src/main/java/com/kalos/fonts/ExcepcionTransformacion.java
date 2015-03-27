/*
 * Created on Nov 25, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.kalos.fonts;

/**
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ExcepcionTransformacion extends RuntimeException {


    private String palabra;
    private int operacion;
    public static final int BETA_A_COMPLETO=0;
	public static final int COMPLETO_A_BETA=1;
	public static final int COMPLETO_A_UNICODE=2;
	
    
	public ExcepcionTransformacion(int operacion, String palabra, Exception ex) {
	   super(ex);
	   this.operacion=operacion;
	   this.palabra=palabra;
	   
	}
	
	public String getMessage(){
      StringBuffer sb=new StringBuffer(200);
      sb.append("problemas al hacer transformación tipo " +  operacion + "\n");
	  sb.append("sobre la palabra " +  palabra + "\n");
	  sb.append("lista de letras:  \n");
	  for (int i=0; i<palabra.length(); i++){
	  	int valInt=(int)palabra.charAt(i);
	  	sb.append("    letra número" + i + " = " + palabra.charAt(i) + "  valor entero=" + valInt + " valor hexadecimal=" + Integer.toString(valInt, 16));
	  }
      return sb.toString();
	}

  
}
