/* 
 * Created on 17/03/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.operaciones.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import kalos.enumeraciones.CompLetras;
import kalos.operaciones.OpPalabras;

public class Transformaciones extends  TestCase implements CompLetras {

	public Transformaciones(String metodo){
	  super(metodo);
	}


	public void testBetaACompleto(){
		String enCaracter, enBeta;
		enCaracter=new String(new char[]{cIotaAsperoAgudoCorta, cEta, cMu, cIotaCorta});
		enBeta="I(/HMI";
	  Assert.assertEquals(OpPalabras.strBetaACompleto(enBeta), enCaracter);
	  
	  enCaracter=new String(new char[]{cDelta, cAlfaCorta, cIotaDieresisAgudoCorta, cSigmaFinal});
	  enBeta="DAI+/J";
	  Assert.assertEquals(OpPalabras.strBetaACompleto(enBeta), enCaracter);
	  
	  //iota subscripta
	  enCaracter=new String(new char[]{cAlfaSubscriptaSuave, cDelta, cOmega});
	  enBeta=OpPalabras.strBetaACompleto("A|)DW");
	  assertEquals(enCaracter, enBeta);
	  
	  //timw|o
	  enCaracter=new String(new char[]{cTau, cIotaCorta, cMu, cOmegaSubscriptaCircunflejo, cOmicron});
	  enBeta=OpPalabras.strBetaACompleto("TIMW|=O");
		assertEquals(enCaracter, enBeta);
		
		//AI larga, impl'icita por el circunflejo
		enCaracter=new String(new char[]{cAlfaSuaveCorta, cRho, cJi, cAlfaCorta, cIotaCircunflejo, cNu});
		enBeta=OpPalabras.strBetaACompleto("A)RCAI=N");
		assertEquals(enCaracter, enBeta);
		
		enCaracter=new String(new char[]{cAlfaSuaveCorta, cRho, cJi, cAlfaCorta, cIotaCircunflejo});
		enBeta=OpPalabras.strBetaACompleto("A)RCAI=");
		assertEquals(enCaracter, enBeta);

	}
	
	
	public void testCompletoABeta(){
		testCABGen(new char[]{cTau, cIotaCorta, cMu, cOmegaSubscriptaCircunflejo, cOmicron}, "TIMW|=O") ;
		testCABGen(new char[]{cDelta, cOmicron, cUpsilonCircunflejo}, "DOU=") ;
	}
	
	public void testCABGen(char[] arrCaracteres, String literal ){
		String deCaracteres=OpPalabras.strCompletoABeta(new String(arrCaracteres));
		System.out.println(deCaracteres);
		assertEquals(deCaracteres, literal);
		
	}

  



	public static void main(String[] args){
	  new Transformaciones("testBetaACompleto");
	}
}
