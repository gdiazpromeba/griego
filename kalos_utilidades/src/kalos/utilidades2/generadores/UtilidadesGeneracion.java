/*
 * Created on May 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kalos.utilidades.generadores;

/**
 * @author Gonzalo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UtilidadesGeneracion {

	/**
	 * escapa las cadenas en código Beta que serán insertadas en los campos de 
	 * "regular expression"
	 * @param cadenaBeta
	 * @return
	 */
	public static String escapaBetasRegexp(String cadenaBeta){
		String cadena=cadenaBeta.replaceAll("\\|", "\\\\|");
		cadena=cadena.replaceAll("\\)", "\\\\)");
		cadena=cadena.replaceAll("\\(", "\\\\(");
		return cadena;
	}

}
