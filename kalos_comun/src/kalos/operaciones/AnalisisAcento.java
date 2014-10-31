// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.operaciones;

import kalos.enumeraciones.Acento;

;

// Referenced classes of package kalos.G:
//            Silaba, OpPalabras

public class AnalisisAcento {
    public static class Actual {

	public Acento tipoAcento;
	public int silaba;
	public int silabaB1;
	public boolean licitoEstricto;
	public int indiceLetraAcentuada;
	//public int E;
	public boolean esTipoAcentonaturalDadaPosicion;

	public Actual() {
	    indiceLetraAcentuada = -1;
	    silabaB1 = 0;
	}
    }

    public static class Sugerido {

	public Acento tipoAcento;
	public int silaba;
	public int silabaB1;
	public boolean llegoSinCumplir;

	public Sugerido() {
	}
    }

    private AnalisisAcento() {
	sugeridos = new Sugerido();
	actuales = new Actual();
    }

    public void dump() {
	System.out.println((new StringBuilder()).append(" cantidad de sílabas= ").append(cantidadDeSilabas).toString());
	System.out.println("sugeridos****");
	System.out.println((new StringBuilder()).append("  tipo de acento=").append(sugeridos.tipoAcento).toString());
	System.out.println((new StringBuilder()).append("  silaba=").append(sugeridos.silaba).toString());
	System.out.println((new StringBuilder()).append("  silabaB1=").append(sugeridos.silabaB1).toString());
	System.out.println((new StringBuilder()).append("  lleguó sin cumplir=").append(sugeridos.llegoSinCumplir).toString());
	System.out.println("actuales*****");
	System.out.println((new StringBuilder()).append("  tipo de acento=").append(actuales.tipoAcento).toString());
	System.out.println((new StringBuilder()).append("  índice del carácter acentuado=").append(actuales.indiceLetraAcentuada).toString());
	System.out.println((new StringBuilder()).append("  silaba=").append(actuales.silaba).toString());
	System.out.println((new StringBuilder()).append("  silabaB1=").append(actuales.silabaB1).toString());
	System.out.println((new StringBuilder()).append("  lícito estricto=").append(actuales.licitoEstricto).toString());
    }

    private int averiguaSilabaB1() {
	int i = 0;
	for (int j = 0; j < silabas.length; j++) {
	    Object aobj[] = OpPalabras.contieneAcento(silabas[j].getCadena());
	    if (!aobj[0].equals(Acento.Ninguno))
		i++;
	}

	return i;
    }

    public static AnalisisAcento getAnalisisAcento(String s) {
	AnalisisAcento d = new AnalisisAcento();
	d.silabas = OpPalabras.silabea(s);
	d.cantidadDeSilabas = d.silabas.length;
	Object aobj[] = new Object[2];
	aobj = OpPalabras.contieneAcento(s);
	d.actuales.tipoAcento = (Acento) aobj[0];
	d.actuales.indiceLetraAcentuada = ((Integer) aobj[1]).intValue();
	d.analizaSilabas();
	d.actuales.silabaB1 = d.averiguaSilabaB1();
	d.actuales.esTipoAcentonaturalDadaPosicion = d.esNaturalDadaPosicion();
	d.sugiere();
	d.averiguaLicitoEstricto();
	return d;
    }

    public static AnalisisAcento getAnalisisAcento(Silaba ao[]) {
	AnalisisAcento d = new AnalisisAcento();
	d.silabas = ao;
	d.cantidadDeSilabas = d.silabas.length;
	d.actuales.tipoAcento = Acento.Ninguno ;
	int i = 0;
	int j = 0;
	do {
	    if (j >= ao.length)
		break;
	    Object aobj[] = OpPalabras.contieneAcento(ao[j].getCadena());
	    if (!aobj[0].equals(Acento.Ninguno)) {
		d.actuales.indiceLetraAcentuada = i + ((Integer) aobj[1]).intValue();
		d.actuales.tipoAcento = (Acento) aobj[0];
		break;
	    }
	    i += ao[j].getCadena().length();
	    j++;
	} while (true);
	//d.actuales.E = d.averiguaSilabaB1();
	d.sugiere();
	d.averiguaLicitoEstricto();
	d.actuales.esTipoAcentonaturalDadaPosicion = d.esNaturalDadaPosicion();
	return d;
    }

    private void analizaSilabas() {
	for (int i = 0; i < silabas.length; i++) {
	    Object aobj[] = OpPalabras.contieneAcento(silabas[i].getCadena());
	    Acento e = (Acento) aobj[0];
	    if (!e.equals(Acento.Ninguno)) {
		actuales.silaba = i - silabas.length;
		actuales.silabaB1 = cantidadDeSilabas + actuales.silaba + 1;
	    }
	}

    }

    private void sugiere() {
	int largoAcumulado = 0;
	int j = 0;
	int punteroSilaba = silabas.length - 1;
	do {
	    j = largoAcumulado;
	    if (silabas[punteroSilaba].esLarga())
		largoAcumulado += 2;
	    else
		largoAcumulado++;
	    punteroSilaba--;
	} while (largoAcumulado < 2 && punteroSilaba >= 0);
	if (punteroSilaba < 0) {
	    sugeridos.silaba = -silabas.length;
	    sugeridos.llegoSinCumplir = true;
	    if (j >= 2)
		sugeridos.tipoAcento = Acento.Agudo;
	    else if (!silabas[0].esLarga())
		sugeridos.tipoAcento = Acento.Agudo;
	    else
		sugeridos.tipoAcento = Acento.Circunflejo;
	} else {
	    sugeridos.silaba = punteroSilaba - silabas.length;
	    sugeridos.llegoSinCumplir = false;
	    sugeridos.tipoAcento = Acento.Agudo;
	}
	sugeridos.silabaB1 = silabas.length + sugeridos.silaba + 1;
    }

    private boolean esNaturalDadaPosicion() {
	int i = actuales.silabaB1 - 1;
	Acento e = actuales.tipoAcento;
	if (e == Acento.Grave)
	    return i == silabas.length;
	int j = 0;
	for (int k = i + 1; k < silabas.length; k++) {
	    Silaba o = silabas[k];
	    if (o.esLarga())
		j += 2;
	    else
		j++;
	}

	if (e == Acento.Circunflejo)
	    return j < 2;
	if (e == Acento.Agudo)
	    return j >= 2 || !silabas[i].esLarga();
	else
	    return true;
    }

    private void averiguaLicitoEstricto() {
	if (sugeridos.tipoAcento.equals(actuales.tipoAcento) && sugeridos.silaba == actuales.silaba)
	    actuales.licitoEstricto = true;
	else
	    actuales.licitoEstricto = false;
    }

    public Silaba silabas[];
    public int cantidadDeSilabas;
    public Sugerido sugeridos;
    public Actual actuales;
}
