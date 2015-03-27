/*
 * Created on 28/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.kalos.operaciones;

import junit.framework.TestCase;
import com.kalos.operaciones.Comparador;
import com.kalos.operaciones.OpPalabras;
;

/**
 * @author gonzalo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ComparadorTest extends TestCase {

	/**
	 * Constructor for ComparadorTest.
	 * @param arg0
	 */
	public ComparadorTest(String arg0) {
		super(arg0);
	}

	final private void comparacionGenerica(String primera, String segunda, int resultadoEsperado) {
		String primeraCompleta=OpPalabras.strBetaACompleto(primera);
		String segundaCompleta=OpPalabras.strBetaACompleto(segunda);
		int resultadoObtenido=Comparador.compara(primeraCompleta, segundaCompleta);
		assertTrue(resultadoObtenido==resultadoEsperado);
	}
	
	public void testComparaciones(){
		comparacionGenerica("*A)GHSI/LAOJ", "A)GHSI/LAOJ", 0);
		comparacionGenerica("A)GH/", "A)/GHMA", -1);
		comparacionGenerica("A)/GHMA", "A)GH/", 1);
	}
	
	

}
