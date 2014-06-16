package kalos.utilidades.abms.utilidades;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Espiritu;
import kalos.operaciones.AnalisisAcento;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Recursos;

/**
 * Métodos utilitarios usados para validar y/o autocorregir beans antes de
 * insertarlos. Se llaman desde el método validaPreInsercion de los paneles de
 * detalle
 * 
 * @author gdiaz
 * 
 */
public class UtilValidacion {
    
//    private static Logger logger=Logger.getLogger(UtilValidacion.class.getName());

	/**
	 * las propiedades indicadas del bean, que se suponen en código BETA, las
	 * pasa a mayúscula
	 * 
	 * @param bean
	 * @param properties
	 */
	public static void mayusculaBeta(JTextField[] textos) {
		for (int i = 0; i < textos.length; i++) {
			String cadena = textos[i].getText();
			if (cadena.equals("") || cadena.equals(Recursos.getCadena("nulo")))
				continue;
			cadena = cadena.toUpperCase();
			textos[i].setText(cadena);
		}
	}
	
	/**
	 * devuelve verdadero si el texto es null, es la cadena [null] o su equivalente en el idioma local,
	 * o si después de trimearla es ""
	 * @param texto
	 * @return
	 */
	public static boolean esVaciaONula(String texto){
		if (texto==null)
			return true;
		else if (texto.trim().equals("")){
			return true;
		}else if (texto.equals(Recursos.getCadena("nulo"))){
			return true;
		}else{
			return false;
		}
	}
		
	/**
	 * para las propiedades indicadas del bean, verifica si tienen espíritu, y
	 * si no lo tienen les pone espíritu suave
	 * 
	 * @param bean
	 * @param properties
	 */
	public static void espituSuave(JTextField[] textos) {
		for (int i = 0; i < textos.length; i++) {
			String cadena = textos[i].getText();

			if (cadena.equals("") || cadena.equals(Recursos.getCadena("nulo")))
				continue;
			cadena = OpPalabras.strBetaACompleto(cadena);
			if (OpPalabras.getEspiritu(cadena) == Espiritu.Ninguno) {
				cadena = OpPalabras.espirituPalabra(cadena, Espiritu.Suave);
			}
			cadena = OpPalabras.strCompletoABeta(cadena);
			textos[i].setText(cadena);
		}
	}

	/**
	 * para las propiedades indicadas en el bean, verifica que las palabras
	 * terminadas en sigma lo sean con sigma final; en caso contrario las
	 * corrige
	 * 
	 * @param bean
	 * @param properties
	 */
	public static void sigmaFinal(JTextField[] textos) {
		for (int i = 0; i < textos.length; i++) {
			String cadena = textos[i].getText();
			if (cadena.equals("") || cadena.equals(Recursos.getCadena("nulo")))
				continue;
			cadena = OpPalabras.strBetaACompleto(cadena);
			cadena = OpPalabras.sigmaFinal(cadena);
			cadena = OpPalabras.strCompletoABeta(cadena);
			textos[i].setText(cadena);
		}
	}

	/**
	 * devuelve "false" si alguna de las propiedades indicadas del bean no tiene
	 * acento
	 * 
	 * @param bean
	 * @param properties
	 * @return
	 */
	public static boolean todoAcentuado(JTextField[] textos) {
		for (int i = 0; i < textos.length; i++) {
			String cadenaBeta = textos[i].getText();
			if (cadenaBeta.equals("") || cadenaBeta.equals(Recursos.getCadena("nulo")))
				continue;
			String cadenaCompleta = OpPalabras.strBetaACompleto(cadenaBeta);
			AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(cadenaCompleta);
			if (aa.actuales.tipoAcento == Acento.Ninguno){
				return false;
			}
		}
		return true;
	}

}
