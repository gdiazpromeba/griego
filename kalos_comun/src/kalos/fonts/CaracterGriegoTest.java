/*
 * Created on Apr 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package kalos.fonts;

import junit.framework.TestCase;
import kalos.enumeraciones.CompLetras;

/**
 * @author GDiaz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CaracterGriegoTest extends TestCase {
	public CaracterGriegoTest(String metodo){
	  super(metodo);
	}
	
	public static void main(String[] args){
	  new CaracterGriegoTest("testAtributos");
	}
	
	public void testAtributos(){
		char a=CompLetras.cAlfaSuaveAgudoCortaMayuscula;
		CaracterGriego cag=CaracterGriegoFactory.produceCaracterGriego(a);
		assertTrue(cag.esVocal());
		assertTrue(cag.esSuave());
		assertTrue(cag.tieneAcentoAgudo());
		assertTrue(cag.esMayuscula());

	}
}
