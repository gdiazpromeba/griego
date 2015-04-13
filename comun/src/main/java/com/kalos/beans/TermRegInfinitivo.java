// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.beans;

import java.util.List;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.TiempoOAspecto;
import com.kalos.enumeraciones.Voz;
import com.kalos.operaciones.OpBeans;

// Referenced classes of package kalos.K:
//            G

public class TermRegInfinitivo implements TermRegVerbal, TieneTemaPropuesto, Verboide {



	public int hashCode() {
		if (hashCode == null)
			hashCode = Integer.valueOf(cadena());
		return hashCode.intValue();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TermRegInfinitivo)
			return hashCode() == obj.hashCode();
		else
			return false;
	}

	private int cadena() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(voz);
		stringbuffer.append("|");
		stringbuffer.append(aspecto);
		stringbuffer.append("|");
		stringbuffer.append(fuerte);
		stringbuffer.append("|");
		stringbuffer.append(tipoDesinencia);
		stringbuffer.append("|");
		stringbuffer.append(terminacion);
		stringbuffer.append("|");
		stringbuffer.append(regEx);
		stringbuffer.append("|");
		stringbuffer.append(silaba);
		stringbuffer.append("|");
		stringbuffer.append(acento);
		stringbuffer.append("|");
		stringbuffer.append(contraccionGeneradora);
		stringbuffer.append("|");
		stringbuffer.append(formaOriginal);
		stringbuffer.append("|");
		stringbuffer.append(tipoVerboExtendido);
		stringbuffer.append("|");
		stringbuffer.append(formaOriginalCompuesta);
		stringbuffer.append("|");
		stringbuffer.append(preposiciones);
		stringbuffer.append("|");
		stringbuffer.append(formaADestransformar);
		stringbuffer.append("|");
		stringbuffer.append(temaPropuesto);
		stringbuffer.append("|");
		stringbuffer.append(idVerbo);
		stringbuffer.append("|");
		stringbuffer.append(particularidad);
		return stringbuffer.toString().hashCode();
	}

	public String getTemaPropuesto() {
		return temaPropuesto;
	}

	public void setTemaPropuesto(String s) {
		temaPropuesto = s;
		hashCode = null;
	}

	public String getFormaDestransformada() {
		return formaDestransformada;
	}

	public void setFormaDestransformada(String s) {
		formaDestransformada = s;
		hashCode = null;
	}

	public Acento getAcento() {
		return acento;
	}

	public void setAcento(Acento e) {
		acento = e;
		hashCode = null;
	}

	public FuerteDebil getFuerte() {
		return fuerte;
	}

	public void setFuerte(FuerteDebil p) {
		fuerte = p;
		hashCode = null;
	}

	public String getRegEx() {
		return regEx;
	}

	public void setRegEx(String s) {
		regEx = s;
		hashCode = null;
	}

	public Silaba getSilaba() {
		return silaba;
	}

	public void setSilaba(Silaba i) {
		silaba = i;
		hashCode = null;
	}

	public int getTipoDesinencia() {
		return tipoDesinencia;
	}

	public void setTipoDesinencia(int i) {
		tipoDesinencia = i;
		hashCode = null;
	}

	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz z) {
		voz = z;
		hashCode = null;
	}

	public String getTerminacion() {
		return terminacion;
	}

	public void setTerminacion(String s) {
		terminacion = s;
		hashCode = null;
	}

	public String getFormaOriginal() {
		return formaOriginal;
	}

	public void setFormaOriginal(String s) {
		formaOriginal = s;
		hashCode = null;
	}

	public int getTipoVerboExtendido() {
		return tipoVerboExtendido;
	}

	public void setTipoVerboExtendido(int i) {
		tipoVerboExtendido = i;
		hashCode = null;
	}

	public String getFormaOriginalCompuesta() {
		return formaOriginalCompuesta;
	}

	public void setFormaOriginalCompuesta(String s) {
		formaOriginalCompuesta = s;
		hashCode = null;
	}

	public List getPreposiciones() {
		return preposiciones;
	}

	public void setPreposiciones(List list) {
		preposiciones = list;
		hashCode = null;
	}

	public String getFormaADestransformar() {
		return formaADestransformar;
	}

	public void setFormaADestransformar(String s) {
		formaADestransformar = s;
		hashCode = null;
	}

	public String getIdVerbo() {
		return idVerbo;
	}

	public void setIdVerbo(String s) {
		idVerbo = s;
		hashCode = null;
	}

	public Particularidad getParticularidad() {
		return particularidad;
	}

	public void setParticularidad(Particularidad x) {
		particularidad = x;
		hashCode = null;
	}
	
	
	public Aspecto getAspecto() {
		return aspecto;
	}

	public TiempoOAspecto getTiempoOAspecto() {
		return aspecto;
	}

	public void setAspecto(Aspecto k) {
		aspecto = k;
		hashCode = null;
	}

	public Contraccion getContraccionGeneradora() {
		return contraccionGeneradora;
	}

	public void setContraccionGeneradora(Contraccion c) {
		contraccionGeneradora = c;
		hashCode = null;
	}

	public boolean isCompuesto() {
		return compuesto;
	}

	public void setCompuesto(boolean flag) {
		compuesto = flag;
	}

	public String getIdVerboCompuesto() {
		return idVerboCompuesto;
	}

	public void setIdVerboCompuesto(String s) {
		idVerboCompuesto = s;
	}

	public TermRegInfinitivo clona() {
		return (TermRegInfinitivo) OpBeans.clona(this);
	}

	private Voz voz;
	private Aspecto aspecto;
	private FuerteDebil fuerte;
	private int tipoDesinencia;
	private String terminacion;
	private String regEx;
	private Silaba silaba;
	private Acento acento;
	private Contraccion contraccionGeneradora;
	private String formaOriginal;
	private int tipoVerboExtendido;
	private String formaOriginalCompuesta;
	private List preposiciones;
	private String formaADestransformar;
	private String formaDestransformada;
	private String temaPropuesto;
	private String idVerbo;
	private Particularidad particularidad;
	private boolean compuesto;
	private String idVerboCompuesto;
	Integer hashCode;
}
