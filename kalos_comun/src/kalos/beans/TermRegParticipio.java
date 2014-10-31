// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.beans;

import java.util.List;


import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.OrigenTema;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.TiempoOAspecto;
import kalos.enumeraciones.Voz;
import kalos.operaciones.OpBeans;

// Referenced classes of package kalos.K:
//            G, T

public class TermRegParticipio implements TermRegNominal, TermRegVerbal {

	public int hashCode() {
		if (x == null)
			x = Integer.valueOf(cadena());
		return x.intValue();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TermRegParticipio)
			return hashCode() == obj.hashCode();
		else
			return false;
	}

	private int cadena() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(d);
		stringbuffer.append("|");
		stringbuffer.append(caso);
		stringbuffer.append("|");
		stringbuffer.append(genero);
		stringbuffer.append("|");
		stringbuffer.append(origenTema);
		stringbuffer.append("|");
		stringbuffer.append(X);
		stringbuffer.append("|");
		stringbuffer.append(h);
		stringbuffer.append("|");
		stringbuffer.append(_);
		stringbuffer.append("|");
		stringbuffer.append(t);
		stringbuffer.append("|");
		stringbuffer.append(c);
		stringbuffer.append("|");
		stringbuffer.append(v);
		stringbuffer.append(b);
		stringbuffer.append("|");
		stringbuffer.append(u);
		stringbuffer.append("|");
		stringbuffer.append(p);
		stringbuffer.append("|");
		stringbuffer.append(y);
		stringbuffer.append("|");
		stringbuffer.append(i);
		stringbuffer.append("|");
		stringbuffer.append(W);
		stringbuffer.append("|");
		stringbuffer.append(l);
		stringbuffer.append("|");
		stringbuffer.append(A3);
		stringbuffer.append("|");
		stringbuffer.append(BA);
		stringbuffer.append("|");
		stringbuffer.append(numero);
		stringbuffer.append("|");
		stringbuffer.append(preposiciones);
		stringbuffer.append("|");
		stringbuffer.append(B5);
		stringbuffer.append("|");
		stringbuffer.append(C1);
		stringbuffer.append("|");
		stringbuffer.append(a);
		stringbuffer.append("|");
		stringbuffer.append(C0);
		stringbuffer.append("|");
		stringbuffer.append(e);
		stringbuffer.append("|");
		stringbuffer.append(A2);
		stringbuffer.append("|");
		stringbuffer.append(o);
		stringbuffer.append("|");
		stringbuffer.append(s);
		stringbuffer.append("|");
		stringbuffer.append(n);
		stringbuffer.append("|");
		stringbuffer.append(Y);
		stringbuffer.append("|");
		stringbuffer.append(r);
		stringbuffer.append("|");
		stringbuffer.append(z);
		return stringbuffer.toString().hashCode();
	}

	public Acento getAcento() {
		return t;
	}

	public void setAcento(Acento e1) {
		t = e1;
		x = null;
	}

	public int getAcentoConcuerda() {
		return m;
	}

	public void setAcentoConcuerda(int i1) {
		m = i1;
		x = null;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso a1) {
		caso = a1;
		x = null;
	}

	public boolean isExContraccion() {
		return _;
	}

	public void setExContraccion(boolean flag) {
		_ = flag;
		x = null;
	}

	public int getIdTipoSustantivo() {
		return AA;
	}

	public void setIdTipoSustantivo(int i1) {
		AA = i1;
		x = null;
	}

	public Numero getNumero() {
		return numero;
	}

	public void setNumero(Numero n1) {
		numero = n1;
		x = null;
	}

	public int getPosicionConcuerda() {
		return j;
	}

	public void setPosicionConcuerda(int i1) {
		j = i1;
		x = null;
	}

	public void setOrigenTema(OrigenTema g1) {
		origenTema = g1;
		x = null;
	}

	public OrigenTema getOrigenTema() {
		return origenTema;
	}

	public String getRegExDesinencia() {
		return c;
	}

	public void setRegExDesinencia(String s1) {
		c = s1;
		x = null;
	}

	public int getSilaba() {
		return v;
	}

	public void setSilaba(int i1) {
		v = i1;
		x = null;
	}

	public int getSubindice() {
		return X;
	}

	public void setSubindice(int i1) {
		X = i1;
		x = null;
	}

	public String getTerminacion() {
		return h;
	}

	public void setTerminacion(String s1) {
		h = s1;
		x = null;
	}

	public String getTiposHoja() {
		return A4;
	}

	public void setTiposHoja(String s1) {
		A4 = s1;
		x = null;
	}

	public int getTipoSustantivo() {
		return d;
	}

	public void setTipoSustantivo(int i1) {
		d = i1;
		x = null;
	}

	public String getFormaOriginal() {
		return b;
	}

	public void setFormaOriginal(String s1) {
		b = s1;
		x = null;
	}

	public TermRegNominal getTerminacionPendienteRevision() {
		return u;
	}

	public void setTerminacionPendienteRevision(TermRegNominal t1) {
		u = t1;
		x = null;
	}

	public String getGenitivoPropuesto() {
		return y;
	}

	public void setGenitivoPropuesto(String s1) {
		y = s1;
		x = null;
	}

	public String getNominativoPropuesto() {
		return p;
	}

	public void setNominativoPropuesto(String s1) {
		p = s1;
		x = null;
	}

	public boolean isPudeAcento() {
		return i;
	}

	public void setPudeAcento(boolean flag) {
		i = flag;
		x = null;
	}

	public String getFormaADestransformar() {
		return W;
	}

	public void setFormaADestransformar(String s1) {
		W = s1;
		x = null;
	}

	public String getIdVerbo() {
		return l;
	}

	public void setIdVerbo(String s1) {
		l = s1;
		x = null;
	}

	public Particularidad getParticularidad() {
		return B5;
	}

	public void setParticularidad(Particularidad x1) {
		B5 = x1;
		x = null;
	}

	public List<String> getPreposiciones() {
		return preposiciones;
	}

	public void setPreposiciones(List<String> list) {
		preposiciones = list;
		x = null;
	}

	public int getTipoDesinencia() {
		return C1;
	}

	public void setTipoDesinencia(int i1) {
		C1 = i1;
		x = null;
	}

	public int getTipoVerboExtendido() {
		return a;
	}

	public void setTipoVerboExtendido(int i1) {
		a = i1;
		x = null;
	}

	public Voz getVoz() {
		return A3;
	}

	public void setVoz(Voz z1) {
		A3 = z1;
		x = null;
	}

	public String getFormaDestransformada() {
		return e;
	}

	public void setFormaDestransformada(String s1) {
		e = s1;
		x = null;
	}

	public String getFormaOriginalCompuesta() {
		return A2;
	}

	public void setFormaOriginalCompuesta(String s1) {
		A2 = s1;
		x = null;
	}

	public FuerteDebil getFuerte() {
		return C0;
	}

	public void setFuerte(FuerteDebil p1) {
		C0 = p1;
		x = null;
	}

	public TiempoOAspecto getAspecto() {
		return BA;
	}

	public TiempoOAspecto getTiempoOAspecto() {
		return getAspecto();
	}

	public void setAspecto(Aspecto k1) {
		BA = k1;
		x = null;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero a1) {
		genero = a1;
		x = null;
	}

	public String getFormaCanonica() {
		return o;
	}

	public void setFormaCanonica(String s1) {
		o = s1;
		x = null;
	}

	public String getTemaPropuesto() {
		return s;
	}

	public void setTemaPropuesto(String s1) {
		s = s1;
		x = null;
	}

	public int getJuego() {
		return n;
	}

	public void setJuego(int i1) {
		n = i1;
		x = null;
	}

	public int getContraccionComedora() {
		return Y;
	}

	public void setContraccionComedora(int i1) {
		Y = i1;
		x = null;
	}

	public String getTerminacionVerbalizada() {
		return r;
	}

	public void setTerminacionVerbalizada(String s1) {
		r = s1;
		x = null;
	}

	public int getTipoVerbo() {
		return z;
	}

	public void setTipoVerbo(int i1) {
		z = i1;
		x = null;
	}

	public boolean isCompuesto() {
		return Z;
	}

	public void setCompuesto(boolean flag) {
		Z = flag;
	}

	public String getIdVerboCompuesto() {
		return f;
	}

	public void setIdVerboCompuesto(String s1) {
		f = s1;
	}

	public TermRegParticipio clona() {
		return (TermRegParticipio) OpBeans.clona(this);
	}

	private int d;
	private int AA;
	private Genero genero;
	private int X;
	private String h;
	private String A4;
	private boolean _;
	private int m;
	private int j;
	private Acento t;
	private String c;
	private int v;
	private String b;
	private TermRegNominal u;
	private String p;
	private String y;
	private boolean i;
	private String W;
	private String l;
	private Voz A3;
	private TiempoOAspecto BA;
	private List<String> preposiciones;
	private Particularidad B5;
	private int C1;
	private int a;
	private FuerteDebil C0;
	private Numero numero;
	private String e;
	private String A2;
	private String o;
	private String s;
	private int n;
	private int Y;
	private String r;
	private int z;
	private boolean Z;
	private String f;
	private Caso caso;
	private OrigenTema origenTema;
	Integer x;
}
