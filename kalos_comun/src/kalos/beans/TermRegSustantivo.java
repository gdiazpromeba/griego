// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.beans;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.OrigenTema;

// Referenced classes of package kalos.K:
//            T

public class TermRegSustantivo implements TermRegNominal {

	public boolean isAceptaDieresis() {
		return B;
	}

	public void setAceptaDieresis(boolean flag) {
		B = flag;
	}

	public String getRegexDieresis() {
		return N;
	}

	public void setRegexDieresis(String s) {
		N = s;
	}

	public int hashCode() {
		if (hashCodePropio == null)
			hashCodePropio = Integer.valueOf(cadena());
		return hashCodePropio.intValue();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TermRegSustantivo)
			return hashCode() == obj.hashCode();
		else
			return false;
	}

	protected int cadena() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(R);
		stringbuffer.append("|");
		stringbuffer.append(F);
		stringbuffer.append("|");
		stringbuffer.append(I);
		stringbuffer.append("|");
		stringbuffer.append(E);
		stringbuffer.append("|");
		stringbuffer.append(D);
		stringbuffer.append("|");
		stringbuffer.append(C);
		stringbuffer.append("|");
		stringbuffer.append(P);
		stringbuffer.append("|");
		stringbuffer.append(T);
		stringbuffer.append("|");
		stringbuffer.append(S);
		stringbuffer.append("|");
		stringbuffer.append(U);
		stringbuffer.append("|");
		stringbuffer.append(H);
		stringbuffer.append("|");
		stringbuffer.append(L);
		stringbuffer.append("|");
		stringbuffer.append(A);
		return stringbuffer.toString().hashCode();
	}

	public Acento getAcento() {
		return S;
	}

	public void setAcento(Acento e) {
		S = e;
		hashCodePropio = null;
	}

	public int getAcentoConcuerda() {
		return G;
	}

	public void setAcentoConcuerda(int i) {
		G = i;
		hashCodePropio = null;
	}

	public Caso getCaso() {
		return F;
	}

	public void setCaso(Caso a) {
		F = a;
		hashCodePropio = null;
	}

	public boolean isExContraccion() {
		return P;
	}

	public void setExContraccion(boolean flag) {
		P = flag;
		hashCodePropio = null;
	}

	public int getIdTipoSustantivo() {
		return O;
	}

	public void setIdTipoSustantivo(int i) {
		O = i;
		hashCodePropio = null;
	}

	public Numero getNumero() {
		return I;
	}

	public void setNumero(Numero n) {
		I = n;
		hashCodePropio = null;
	}

	public int getPosicionConcuerda() {
		return Q;
	}

	public void setPosicionConcuerda(int i) {
		Q = i;
		hashCodePropio = null;
	}

	public String getRegExDesinencia() {
		return J;
	}

	public void setRegExDesinencia(String s) {
		J = s;
		hashCodePropio = null;
	}

	public int getSilaba() {
		return U;
	}

	public void setSilaba(int i) {
		U = i;
		hashCodePropio = null;
	}

	public int getSubindice() {
		return D;
	}

	public void setSubindice(int i) {
		D = i;
		hashCodePropio = null;
	}

	public String getTerminacion() {
		return C;
	}

	public void setTerminacion(String s) {
		C = s;
		hashCodePropio = null;
	}

	public String getTiposHoja() {
		return H;
	}

	public void setTiposHoja(String s) {
		H = s;
		hashCodePropio = null;
	}

	public int getTipoSustantivo() {
		return R;
	}

	public void setTipoSustantivo(int i) {
		R = i;
		hashCodePropio = null;
	}

	public String getFormaOriginal() {
		return T;
	}

	public void setFormaOriginal(String s) {
		T = s;
		hashCodePropio = null;
	}

	public TermRegNominal getTerminacionPendienteRevision() {
		return K;
	}

	public void setTerminacionPendienteRevision(TermRegNominal t) {
		K = t;
		hashCodePropio = null;
	}

	public String getGenitivoPropuesto() {
		return A;
	}

	public void setGenitivoPropuesto(String s) {
		A = s;
		hashCodePropio = null;
	}

	public String getNominativoPropuesto() {
		return L;
	}

	public void setNominativoPropuesto(String s) {
		L = s;
		hashCodePropio = null;
	}

	public OrigenTema getOrigenTema() {
		return E;
	}

	public void setOrigenTema(OrigenTema g) {
		E = g;
		hashCodePropio = null;
	}

	private int R;
	private int O;
	private Caso F;
	private Numero I;
	private OrigenTema E;
	private int D;
	private String C;
	private String H;
	private boolean P;
	private int G;
	private int Q;
	private Acento S;
	private String J;
	private int U;
	private boolean B;
	private String N;
	private String T;
	private TermRegNominal K;
	private String L;
	private String A;
	protected Integer hashCodePropio;
}
