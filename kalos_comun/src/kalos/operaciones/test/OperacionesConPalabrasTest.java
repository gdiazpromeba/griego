// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.operaciones.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import kalos.enumeraciones.Acento;
import kalos.enumeraciones.CompLetras;
import kalos.enumeraciones.Contraccion;
import kalos.operaciones.AnalisisAcento;
import kalos.operaciones.OpPalabras;

public class OperacionesConPalabrasTest extends TestCase implements CompLetras {

    public OperacionesConPalabrasTest(String s) {
	super(s);
    }

    public void testAcentoCircunflejo() {
	String s = OpPalabras.strBetaACompleto("BHSON");
	String s1 = OpPalabras.strBetaACompleto("BH=SON");
	Assert.assertEquals(OpPalabras.acentua(s), s1);
    }

    public void testAnalisisAcento() {
	String s = OpPalabras.strBetaACompleto("SU_KH=N");
	AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(s);
	assertTrue(aa.actuales.esTipoAcentonaturalDadaPosicion);

	s = OpPalabras.strBetaACompleto("LEI/YW");
	aa = AnalisisAcento.getAnalisisAcento(s);
	assertTrue(aa.actuales.esTipoAcentonaturalDadaPosicion);

	s = OpPalabras.strBetaACompleto("BALEI=N");
	aa = AnalisisAcento.getAnalisisAcento(s);
	assertTrue(aa.actuales.esTipoAcentonaturalDadaPosicion);
    }

    public void testContraccionConsonantica() {
	String s = OpPalabras.strBetaACompleto("SW");
	String s1 = OpPalabras.strBetaACompleto("LEIB");
	String s2 = OpPalabras.strBetaACompleto("LEIYW");
	Assert.assertEquals(OpPalabras.contraeConsonanteSinAcentuar(s1, s), s2);
	s1 = OpPalabras.strBetaACompleto("LEIB");
	String s3 = OpPalabras.strBetaACompleto("LEI/B");
	String s4 = OpPalabras.strBetaACompleto("SW");
	String s5 = OpPalabras.strBetaACompleto("LEI/YW");
	Assert.assertEquals(OpPalabras.contraeConsonante(s1, s, 0, Acento.Ninguno)[2], s5);
	Assert.assertEquals(OpPalabras.contraeConsonante(s1, s, 0, Acento.Ninguno)[1], s4);
	Assert.assertEquals(OpPalabras.contraeConsonante(s1, s, 0, Acento.Ninguno)[0], s3);
    }

    private void contraccionVocalica(String s, String s1, int i, Acento tipoAcento, String s2) {
	String s3 = OpPalabras.strBetaACompleto(s);
	String s4 = OpPalabras.strBetaACompleto(s1);
	String s5 = OpPalabras.contraeVocal(s3, s4, Contraccion.vocalica, i, tipoAcento)[2];
	String s6 = OpPalabras.strCompletoABeta(s5);
	assertEquals(s2, s6);
    }

    public void testContraccionVocalica() {
	contraccionVocalica("TI_MA", "WN", -1, Acento.Ninguno, "TI_MW=N");
	contraccionVocalica("TI_MA", "W", 0, Acento.Ninguno, "TI_MW=");
	contraccionVocalica("TI_MA", "OI_O", -2, Acento.Ninguno, "TI_MW|=O");
	contraccionVocalica("A)GGELE", "ON", -1, Acento.Ninguno, "A)GGELOU=N");
	contraccionVocalica("A)LHQE", "I", -1, Acento.Ninguno, "A)LHQEI=");
	contraccionVocalica("A)LHQE", "I", 3, Acento.Ninguno, "A)LHQEI=");
	contraccionVocalica("OI_)", "E", 0, Acento.Ninguno, "OI)=E");
    }

    public void testBetaACompleto() {
	String s = "*A)/BDHRA";
	String s1 = OpPalabras.strBetaACompleto(s);
	String s2 = new String(new char[] { '\u5F04', '\u03B2', '\u03B4', '\u03B7', '\u03C1', '\u23B1' });
	assertEquals(s1, s2);
    }

    private void A(String s) {
	String s1 = s;
	String s2 = OpPalabras.strBetaACompleto(s1);
	String s3 = OpPalabras.strCompletoABeta(s2);
	assertEquals(s1, s3);
    }

    public void testConversionIdaYVuelta() {
	A("*A)/BDHRA");
	A("*GADEI/RA");
    }

    public void testReduplicacion() {
	String s = OpPalabras.strBetaACompleto("SH");
	String s1 = OpPalabras.strBetaACompleto("SESH");
	Assert.assertEquals(OpPalabras.reduplica(s), s1);
    }

    public void testDesContrae() {
	String s = OpPalabras.strBetaACompleto("TIMOU=MAI");
	String s1 = OpPalabras.strBetaACompleto("OMAI");
	String as[] = OpPalabras.descontrae(s, s1, Contraccion.vocalica);
	for (int i = 0; i < as.length; i++)
	    System.out.println(as[i]);

    }

    public static void main(String args[]) {
	new OperacionesConPalabrasTest("testConversionIdaYVuelta");
    }
}
