/*
 * Created on Aug 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kalos.flexion;

import java.awt.Color;
//import java.awt.Font;

/**
 * @author Gonzalo
 * La información mínima que necesita una celda tipo reporte
 */
public class CeldaReporte {
	
	
	/**
	 * @return Returns the contenido.
	 */
	public String getContenido() {
		return contenido;
	}
	/**
	 * @param contenido The contenido to set.
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	/**
	 * @return Returns the fondo.
	 */
	public Color getFondo() {
		return fondo;
	}
	/**
	 * @param fondo The fondo to set.
	 */
	public void setFondo(Color fondo) {
		this.fondo = fondo;
	}

	private String contenido;
	private Color fondo;
	
	public CeldaReporte(String contenido, Color fondo){
		this.contenido=contenido;
		this.fondo=fondo;
		this.opaca=true;
	}
	
	
	public String debug(){
      StringBuffer astr=new StringBuffer("[CR]");
      astr.append("|" + contenido);
//      if (fondo==null)
//          astr.append("|nulo");
//      else
//          astr.append("|" + fondo.getRed() + "-" + fondo.getGreen() + "-" + fondo.getBlue());
      return astr.toString();
	}
	
	public String toString(){
	  return contenido;
	}
	
	public int compareTo(CeldaReporte objeto){
		if (!(objeto instanceof CeldaReporte)){
			throw new RuntimeException("el objeto " + objeto + " no es de tipo ICeldaReporte");
		}
		CeldaReporte icr=(CeldaReporte)objeto;
		if (contenido==null && icr.getContenido()==null)
			return 0;
		else if (contenido==null)
			return 1;
		else if (icr.getContenido()==null)
		    return -1;
		else
			return contenido.compareTo(icr.getContenido());
	}
	
	public boolean equals(Object obj){
	    if (obj instanceof CeldaReporte){
	        return ((CeldaReporte)obj).compareTo(this)==0;
	    }
	    return false;
	}

	
	private boolean opaca;
	/**
	 * @return Returns the transparentable.
	 */
	public boolean isOpaque() {
		return opaca;
	}
	/**
	 * @param transparentable The transparentable to set.
	 */
	public void setOpaque(boolean valor) {
		this.opaca = valor;
	}

}
