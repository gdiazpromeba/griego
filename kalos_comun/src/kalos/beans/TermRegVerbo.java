// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.beans;

import java.util.List;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.TiempoOAspecto;
import kalos.enumeraciones.Voz;
import kalos.operaciones.OpBeans;

// Referenced classes of package kalos.K:
//            G

public class TermRegVerbo implements TermRegVerbal {

	public int hashCode() {
		if (CF == null)
			CF = Integer.valueOf(cadena());
		return CF.intValue();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TermRegVerbo)
			return hashCode() == obj.hashCode();
		else
			return false;
	}

	private int cadena() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(CA);
		stringbuffer.append("|");
		stringbuffer.append(D2);
		stringbuffer.append("|");
		stringbuffer.append(C6);
		stringbuffer.append("|");
		stringbuffer.append(C2);
		stringbuffer.append("|");
		stringbuffer.append(C5);
		stringbuffer.append("|");
		stringbuffer.append(C9);
		stringbuffer.append("|");
		stringbuffer.append(D6);
		stringbuffer.append("|");
		stringbuffer.append(D3);
		stringbuffer.append("|");
		stringbuffer.append(CB);
		stringbuffer.append("|");
		stringbuffer.append(D5);
		stringbuffer.append("|");
		stringbuffer.append(D0);
		stringbuffer.append("|");
		stringbuffer.append(C4);
		stringbuffer.append("|");
		stringbuffer.append(D4);
		stringbuffer.append("|");
		stringbuffer.append(CD);
		stringbuffer.append("|");
		stringbuffer.append(C3);
		stringbuffer.append("|");
		stringbuffer.append(C7);
		stringbuffer.append("|");
		stringbuffer.append(C8);
		return stringbuffer.toString().hashCode();
	}

	public String getTemaPropuesto() {
		return C3;
	}

	public void setTemaPropuesto(String s) {
		C3 = s;
		CF = null;
	}

	public String getFormaDestransformada() {
		return D1;
	}

	public void setFormaDestransformada(String s) {
		D1 = s;
		CF = null;
	}

	public Acento getAcento() {
		return D3;
	}

	public void setAcento(Acento e) {
		D3 = e;
		CF = null;
	}

	public FuerteDebil getFuerte() {
		return C6;
	}

	public void setFuerte(FuerteDebil p) {
		C6 = p;
		CF = null;
	}

	public String getRegEx() {
		return C9;
	}

	public void setRegEx(String s) {
		C9 = s;
		CF = null;
	}

	public int getSilaba() {
		return D6;
	}

	public void setSilaba(int i) {
		D6 = i;
		CF = null;
	}

	public int getTipoDesinencia() {
		return C2;
	}

	public void setTipoDesinencia(int i) {
		C2 = i;
		CF = null;
	}

	public Voz getVoz() {
		return CA;
	}

	public void setVoz(Voz z) {
		CA = z;
		CF = null;
	}

	public String getTerminacion() {
		return C5;
	}

	public void setTerminacion(String s) {
		C5 = s;
		CF = null;
	}

	public String getFormaOriginal() {
		return D5;
	}

	public void setFormaOriginal(String s) {
		D5 = s;
		CF = null;
	}

	public int getTipoVerboExtendido() {
		return D0;
	}

	public void setTipoVerboExtendido(int i) {
		D0 = i;
		CF = null;
	}

	public String getFormaOriginalCompuesta() {
		return C4;
	}

	public void setFormaOriginalCompuesta(String s) {
		C4 = s;
		CF = null;
	}

	public List getPreposiciones() {
		return D4;
	}

	public void setPreposiciones(List list) {
		D4 = list;
		CF = null;
	}

	public String getFormaADestransformar() {
		return CD;
	}

	public void setFormaADestransformar(String s) {
		CD = s;
		CF = null;
	}

	public String getIdVerbo() {
		return C7;
	}

	public void setIdVerbo(String s) {
		C7 = s;
		CF = null;
	}

	public Particularidad getParticularidad() {
		return C8;
	}

	public void setParticularidad(Particularidad x) {
		C8 = x;
		CF = null;
	}

	public TiempoOAspecto getAspecto() {
		return D2;
	}

	public TiempoOAspecto getTiempoOAspecto() {
		return getAspecto();
	}

	public void setAspecto(Aspecto k) {
		D2 = k;
		CF = null;
	}

	public Contraccion getContraccionGeneradora() {
		return CB;
	}

	public void setContraccionGeneradora(Contraccion c) {
		CB = c;
		CF = null;
	}

	public boolean isCompuesto() {
		return CE;
	}

	public void setCompuesto(boolean flag) {
		CE = flag;
	}

	public String getIdVerboCompuesto() {
		return CC;
	}

	public void setIdVerboCompuesto(String s) {
		CC = s;
	}

	public TermRegVerbo clona() {
		return (TermRegVerbo) OpBeans.clona(this);
	}

	private Voz CA;
	private TiempoOAspecto D2;
	private FuerteDebil C6;
	private int C2;
	private String C5;
	private String C9;
	private int D6;
	private Acento D3;
	private Contraccion CB;
	private String D5;
	private int D0;
	private String C4;
	private List D4;
	private String CD;
	private String D1;
	private String C3;
	private String C7;
	private Particularidad C8;
	private boolean CE;
	private String CC;
	Integer CF;
}
