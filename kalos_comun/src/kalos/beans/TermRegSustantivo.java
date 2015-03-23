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
		return aceptaDieresis;
	}

	public void setAceptaDieresis(boolean flag) {
		aceptaDieresis = flag;
	}

	public String getRegexDieresis() {
		return regexDieresis;
	}

	public void setRegexDieresis(String s) {
		regexDieresis = s;
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
		stringbuffer.append(tipoSustantivo);
		stringbuffer.append("|");
		stringbuffer.append(caso);
		stringbuffer.append("|");
		stringbuffer.append(numero);
		stringbuffer.append("|");
		stringbuffer.append(origenTema);
		stringbuffer.append("|");
		stringbuffer.append(subindice);
		stringbuffer.append("|");
		stringbuffer.append(terminacion);
		stringbuffer.append("|");
		stringbuffer.append(exContraccion);
		stringbuffer.append("|");
		stringbuffer.append(formaOriginal);
		stringbuffer.append("|");
		stringbuffer.append(acento);
		stringbuffer.append("|");
		stringbuffer.append(silaba);
		stringbuffer.append("|");
		stringbuffer.append(tiposHoja);
		stringbuffer.append("|");
		stringbuffer.append(nominativoPropuesto);
		stringbuffer.append("|");
		stringbuffer.append(genitivoPropuesto);
		return stringbuffer.toString().hashCode();
	}

	public Acento getAcento() {
		return acento;
	}

	public void setAcento(Acento e) {
		acento = e;
		hashCodePropio = null;
	}

	public int getAcentoConcuerda() {
		return acentoConcuerda;
	}

	public void setAcentoConcuerda(int i) {
		acentoConcuerda = i;
		hashCodePropio = null;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso a) {
		caso = a;
		hashCodePropio = null;
	}

	public boolean isExContraccion() {
		return exContraccion;
	}

	public void setExContraccion(boolean flag) {
		exContraccion = flag;
		hashCodePropio = null;
	}

	public int getIdTipoSustantivo() {
		return idTipoSustantivo;
	}

	public void setIdTipoSustantivo(int i) {
		idTipoSustantivo = i;
		hashCodePropio = null;
	}

	public Numero getNumero() {
		return numero;
	}

	public void setNumero(Numero n) {
		numero = n;
		hashCodePropio = null;
	}

	public int getPosicionConcuerda() {
		return posicionConcuerda;
	}

	public void setPosicionConcuerda(int i) {
		posicionConcuerda = i;
		hashCodePropio = null;
	}

	public String getRegExDesinencia() {
		return regexDesinencia;
	}

	public void setRegExDesinencia(String s) {
		regexDesinencia = s;
		hashCodePropio = null;
	}

	public int getSilaba() {
		return silaba;
	}

	public void setSilaba(int i) {
		silaba = i;
		hashCodePropio = null;
	}

	public int getSubindice() {
		return subindice;
	}

	public void setSubindice(int i) {
		subindice = i;
		hashCodePropio = null;
	}

	public String getTerminacion() {
		return terminacion;
	}

	public void setTerminacion(String s) {
		terminacion = s;
		hashCodePropio = null;
	}

	public String getTiposHoja() {
		return tiposHoja;
	}

	public void setTiposHoja(String s) {
		tiposHoja = s;
		hashCodePropio = null;
	}

	public int getTipoSustantivo() {
		return tipoSustantivo;
	}

	public void setTipoSustantivo(int i) {
		tipoSustantivo = i;
		hashCodePropio = null;
	}

	public String getFormaOriginal() {
		return formaOriginal;
	}

	public void setFormaOriginal(String s) {
		formaOriginal = s;
		hashCodePropio = null;
	}

	public TermRegNominal getTerminacionPendienteRevision() {
		return terminacionPendienteRevision;
	}

	public void setTerminacionPendienteRevision(TermRegNominal t) {
		terminacionPendienteRevision = t;
		hashCodePropio = null;
	}

	public String getGenitivoPropuesto() {
		return genitivoPropuesto;
	}

	public void setGenitivoPropuesto(String s) {
		genitivoPropuesto = s;
		hashCodePropio = null;
	}

	public String getNominativoPropuesto() {
		return nominativoPropuesto;
	}

	public void setNominativoPropuesto(String s) {
		nominativoPropuesto = s;
		hashCodePropio = null;
	}

	public OrigenTema getOrigenTema() {
		return origenTema;
	}

	public void setOrigenTema(OrigenTema g) {
		origenTema = g;
		hashCodePropio = null;
	}

	private int tipoSustantivo;
	private int idTipoSustantivo;
	private Caso caso;
	private Numero numero;
	private OrigenTema origenTema;
	private int subindice;
	private String terminacion;
	private String tiposHoja;
	private boolean exContraccion;
	private int acentoConcuerda;
	private int posicionConcuerda;
	private Acento acento;
	private String regexDesinencia;
	private int silaba;
	private boolean aceptaDieresis;
	private String regexDieresis;
	private String formaOriginal;
	private TermRegNominal terminacionPendienteRevision;
	private String nominativoPropuesto;
	private String genitivoPropuesto;
	protected Integer hashCodePropio;
}
