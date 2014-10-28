// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.operaciones;

import kalos.enumeraciones.TipoLetraBasico;
import kalos.fonts.CarPos;
import kalos.fonts.TipoLetra;

// Referenced classes of package kalos.G:
//            I

public class Silaba {

    public Silaba(String s) {
	cadena = s;
	esLarga = averiguaSiEsLarga();
	esDiptongo = averiguaSiEsDiptongo();
    }

    public boolean esLarga() {
	return esLarga;
    }

    public boolean esDiptongo() {
	return esDiptongo;
    }

    public String getCadena() {
	return cadena;
    }

    private boolean averiguaSiEsDiptongo() {
	CarPos a = OpPalabras.encuentraVocalHaciaElComienzo(cadena, cadena.length() - 1);
	if (a == null)
	    return false;
	CarPos a1 = CarPos.getCarPos(cadena, a.getPosicion() - 1);
	if (a1 == null || !a1.esVocal())
	    return false;
	else
	    return OpPalabras.esDiptongo(a1.getCaracterGriego(), a.getCaracterGriego());
    }

    private boolean averiguaSiEsLarga() {
	CarPos a = OpPalabras.encuentraVocalHaciaElComienzo(cadena, cadena.length() - 1);
	if (a == null)
	    return false;
	CarPos a1 = CarPos.getCarPos(cadena, a.getPosicion() - 1);
	if (a.esLarga())
	    return true;
	if (a1 == null)
	    return false;
	if (OpPalabras.esDiptongo(a1.getCaracterGriego(), a.getCaracterGriego()))
	    return a.esLarga()
		    || !((a1.getLetraBase() == '\u23B1' || a1.getLetraBase() == '\u03BF')
			    & (a.getLetraBase() == '\u23B9') & (!a1.esLarga()) & (!a.esLarga()));
	else
	    return false;
    }

    public static boolean esAlargable(Silaba o) {
	if (o.esLarga())
	    return false;
	if (tieneAiOi(o.getCadena()))
	    return true;
	char c = '\0';
	int i = 0;
	do {
	    if (i >= o.getCadena().length())
		break;
	    c = CarPos.getCarPos(o.getCadena(), i).getLetraBase();
	    if (TipoLetra.getTipoLetraBasico(c) == 1)
		break;
	    i++;
	} while (true);
	return c == '\u23B1' || c == '\u23B9' || c == '\u23C5';
    }

    public Silaba[] diptongoRompible() {
	if (!esDiptongo())
	    return (new Silaba[] { this });
	CarPos a = null;
	int i = 0;
	do {
	    if (i >= getCadena().length())
		break;
	    CarPos a1 = CarPos.getCarPos(getCadena(), i);
	    if (a1.esVocal()) {
		a = a1;
		break;
	    }
	    i++;
	} while (true);
	if (a.getLetraBase() == '\u23B9' || a.getLetraBase() == '\u23C5')
	    return (new Silaba[] { new Silaba(getCadena().substring(0, a.getPosicion() + 1)),
		    new Silaba(getCadena().substring(a.getPosicion() + 1)) });
	else
	    return (new Silaba[] { this });
    }

    public boolean esDiptongoRompible() {
	Silaba ao[] = diptongoRompible();
	return ao.length > 1;
    }

    public static Silaba versionLarga(Silaba o) {
	StringBuffer stringbuffer = new StringBuffer(o.getCadena());
	int i = stringbuffer.length() - 1;
	do {
	    if (i < 0)
		break;
	    CarPos a = CarPos.getCarPos(stringbuffer.toString(), i);
	    if (a.esVocal()) {
		stringbuffer.setCharAt(i, a.getVersionLarga());
		break;
	    }
	    i--;
	} while (true);
	return new Silaba(stringbuffer.toString());
    }

    public Object clone() {
	return new Silaba(new String(cadena));
    }

    public void cambiaALarga() {
	Silaba o = versionLarga(this);
	if (o.esLarga())
	    esLarga = true;
	cadena = o.getCadena();
    }

    private static boolean tieneAiOi(String s) {
	String s1 = OpPalabras.strBetaACompleto("AI");
	String s2 = OpPalabras.strBetaACompleto("OI");
	return s.indexOf(s1) > -1 || s.indexOf(s2) > -1;
    }

    public Silaba[] rompeDiptongo() {
	CarPos a = OpPalabras.encuentraVocalHaciaElComienzo(cadena, cadena.length() - 1);
	CarPos a1 = CarPos.getCarPos(cadena, a.getPosicion() - 1);
	if (OpPalabras.esDiptongo(a1.getCaracterGriego(), a.getCaracterGriego())) {
	    String s = cadena.substring(0, a1.getPosicion() + 1);
	    String s1 = cadena.substring(a.getPosicion());
	    return (new Silaba[] { new Silaba(s), new Silaba(s1) });
	} else {
	    return (new Silaba[] { this });
	}
    }

    private String cadena;
    private boolean esLarga;
    private boolean esDiptongo;
}
