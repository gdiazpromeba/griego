//Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://www.kpdus.com/jad.html
//Decompiler options: packimports(3) 

package com.kalos.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aumento;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Propagacion;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TiempoOAspecto;
import com.kalos.enumeraciones.Voz;

//Referenced classes of package kalos.K:
//         L

public class TermRegVerbo implements TermRegVerbal, TieneTemaPropuesto, TieneJuego {
    
    protected Integer hashCodePropio;

		public Aumento getAumento() {
				return aumento;
		}

		public void setAumento(Aumento s) {
				aumento = s;
				hashCodePropio = null;
		}

		public Contraccion getContraccion() {
				return contraccion;
		}

		public void setContraccion(Contraccion c) {
				contraccion = c;
				hashCodePropio = null;
		}

		public FuerteDebil getFuerte() {
				return fuerte;
		}

		public void setFuerte(FuerteDebil p) {
				fuerte = p;
				hashCodePropio = null;
		}

		public String getId() {
				return id;
		}

		public void setId(String s) {
				id = s;
				hashCodePropio = null;
		}

		public int getJuego() {
				return juego;
		}

		public void setJuego(int i) {
				juego = i;
				hashCodePropio = null;
		}

		public Modo getModo() {
				return modo;
		}

		public void setModo(Modo p) {
				modo = p;
				hashCodePropio = null;
		}

		public Propagacion getPropagacion() {
				return propagacion;
		}

		public void setPropagacion(Propagacion d) {
				propagacion = d;
				hashCodePropio = null;
		}

		public boolean isReduplicacion() {
				return reduplicacion;
		}

		public void setReduplicacion(boolean flag) {
				reduplicacion = flag;
				hashCodePropio = null;
		}

		public int getSubPart() {
				return subPart;
		}

		public void setSubPart(int i) {
				subPart = i;
				hashCodePropio = null;
		}

		public String getTema() {
				return tema;
		}

		public void setTema(String s) {
				tema = s;
				hashCodePropio = null;
		}

		public Tiempo getTiempo() {
				return tiempo;
		}

		public void setTiempo(Tiempo j) {
				tiempo = j;
				hashCodePropio = null;
		}

		public Tiempo getTiempoJuego() {
				return tiempoJuego;
		}

		public void setTiempoJuego(Tiempo j) {
				tiempoJuego = j;
				hashCodePropio = null;
		}

		public Voz getVoz() {
				return voz;
		}

		public void setVoz(Voz z) {
				voz = z;
				hashCodePropio = null;
		}

		public Voz getVozJuego() {
				return vozJuego;
		}

		public void setVozJuego(Voz z) {
				vozJuego = z;
				hashCodePropio = null;
		}

		public boolean isCompuesto() {
				return compuesto;
		}

		public void setCompuesto(boolean flag) {
				compuesto = flag;
				hashCodePropio = null;
		}

	  public int hashCode() {
	      if (hashCodePropio == null)
	        hashCodePropio = Integer.valueOf(cadena());
	      return hashCodePropio.intValue();
	    }
		



		private int cadena() {
			StringBuilder sb = new StringBuilder();
		  sb.append(id).append("|");
		  sb.append(idVerbo).append("|");
		  sb.append(tipoVerboExtendido).append("|");
			sb.append(tema).append("|");
			sb.append(subPart).append("|");
			sb.append(modo!=null?modo.name():null).append("|");
			sb.append(tiempo!=null?tiempo.name():null).append("|");
			sb.append(voz!=null?voz.name():null).append("|");
			sb.append(fuerte!=null?fuerte.name():null).append("|");
		  sb.append(contraccion!=null?contraccion.name():null).append("|");
			sb.append(aumento!=null?aumento.name():null).append("|");
			sb.append(reduplicacion).append("|");
			sb.append(juego).append("|");
			sb.append(tiempoJuego!=null?tiempoJuego.name():null).append("|");
			sb.append(vozJuego!=null?vozJuego.name():null).append("|");
			sb.append(propagacion!=null?propagacion.name():null).append("|");
			sb.append(pats).append("|");
		  sb.append(compuesto).append("|");
			sb.append(tipoDesinencia).append("|");
			sb.append(terminacion).append("|");
			sb.append(formaADestransformar).append("|");
			sb.append(formaDestransformada).append("|");
			sb.append(formaOriginalCompuesta).append("|");
			sb.append(formaOriginal).append("|");
			sb.append(preposiciones!=null?preposiciones:null);
			sb.append(particularidad!=null?particularidad.name():null).append("|");
			sb.append(idVerboCompuesto).append("|");
			sb.append(persona!=null?persona.name():null).append("|");
			sb.append(temaPropuesto).append("|");
			sb.append(regEx).append("|");
			sb.append(silaba!=null?silaba.name():null).append("|");
		  sb.append(acento!=null?acento.name():null).append("|");
			return sb.toString().hashCode();
		}
		
	  public boolean equals(Object obj) {
	      if (obj instanceof TermRegSustantivo)
	        return hashCode() == obj.hashCode();
	      else
	        return false;
	    }		

		public boolean isPats() {
				return pats;
		}

		public void setPats(boolean flag) {
				pats = flag;
				hashCodePropio = null;
		}

		private String id;
		private String tema;
		private int subPart;
		private Modo modo;
		private Tiempo tiempo;
		private Voz voz;
		private FuerteDebil fuerte;
		private Contraccion contraccion;
		private Aumento aumento;
		private boolean reduplicacion;
		private int juego;
		private Tiempo tiempoJuego;
		private Voz vozJuego;
		private Propagacion propagacion;
		private boolean pats;
		private boolean compuesto;
		private int tipoDesinencia;
		private String formaDestransformada;
		private int tipoVerboExtendido;
		private String terminacion;
		private String formaADestransformar;
		private List<String> preposiciones;
		private String formaOriginalCompuesta;
		private String formaOriginal;
		private String idVerbo;
		private Particularidad particularidad;
		private String idVerboCompuesto;
		private Persona persona;
		private String temaPropuesto;

		public String getTemaPropuesto() {
				return temaPropuesto;
		}

		public void setTemaPropuesto(String temaPropuesto) {
				this.temaPropuesto = temaPropuesto;
				hashCodePropio = null;
		}

		public Persona getPersona() {
				return persona;
		}

		public void setPersona(Persona persona) {
				this.persona = persona;
				hashCodePropio = null;
		}

		public String getRegEx() {
				return regEx;
		}

		public void setRegEx(String regex) {
				this.regEx = regex;
				hashCodePropio = null;
		}

		public Silaba getSilaba() {
				return silaba;
		}

		public void setSilaba(Silaba silaba) {
				this.silaba = silaba;
				hashCodePropio = null;
		}

		public Acento getAcento() {
				return acento;
		}

		public void setAcento(Acento acento) {
				this.acento = acento;
				hashCodePropio = null;
		}

		private String regEx;
		private Silaba silaba;
		private Acento acento;

		public String getIdVerboCompuesto() {
				return idVerboCompuesto;
		}

		public void setIdVerboCompuesto(String idVerboCompuesto) {
				this.idVerboCompuesto = idVerboCompuesto;
				hashCodePropio = null;
		}

		public Particularidad getParticularidad() {
				return particularidad;
		}

		public void setParticularidad(Particularidad particularidad) {
				this.particularidad = particularidad;
				hashCodePropio = null;
		}

		public String getIdVerbo() {
				return idVerbo;
		}

		public void setIdVerbo(String idVerbo) {
				this.idVerbo = idVerbo;
				hashCodePropio = null;
		}

		public String getFormaOriginal() {
				return formaOriginal;
		}

		public void setFormaOriginal(String formaOriginal) {
				this.formaOriginal = formaOriginal;
				hashCodePropio = null;
		}

		public String getFormaOriginalCompuesta() {
				return formaOriginalCompuesta;
		}

		public void setFormaOriginalCompuesta(String formaOriginalCompuesta) {
				this.formaOriginalCompuesta = formaOriginalCompuesta;
				hashCodePropio = null;
		}

		public TiempoOAspecto getTiempoOAspecto() {
				return tiempo;
		}

		public List<String> getPreposiciones() {
				return preposiciones;
		}

		public void setPreposiciones(List<String> preposiciones) {
				this.preposiciones = preposiciones;
				hashCodePropio = null;
		}

		public String getFormaADestransformar() {
				return formaADestransformar;
		}

		public void setFormaADestransformar(String formaADestransformar) {
				this.formaADestransformar = formaADestransformar;
				hashCodePropio = null;
		}

		public String getTerminacion() {
				return terminacion;
		}

		public void setTerminacion(String terminacion) {
				this.terminacion = terminacion;
				hashCodePropio = null;
		}

		public int getTipoVerboExtendido() {
				return tipoVerboExtendido;
		}

		public void setTipoVerboExtendido(int tipoVerboExtendido) {
				this.tipoVerboExtendido = tipoVerboExtendido;
				hashCodePropio = null;
		}

		public String getFormaDestransformada() {
				return formaDestransformada;
		}

		public void setFormaDestransformada(String formaDestransformada) {
				this.formaDestransformada = formaDestransformada;
				hashCodePropio = null;
		}

		public int getTipoDesinencia() {
				return tipoDesinencia;
		}

		public void setTipoDesinencia(int tipoDesinencia) {
				this.tipoDesinencia = tipoDesinencia;
				hashCodePropio = null;
		}

		@Override
		public TermRegVerbo clona() {
				TermRegVerbo n = new TermRegVerbo();
				n.setAcento(this.acento);
				n.setAumento(this.aumento);
				n.setCompuesto(this.compuesto);
				n.setContraccion(this.contraccion);
				n.setFormaADestransformar(formaADestransformar);
				n.setFormaDestransformada(formaDestransformada);
				n.setFormaOriginal(formaOriginal);
				n.setFormaOriginalCompuesta(formaOriginalCompuesta);
				n.setFuerte(fuerte);
				n.setId(id);
				n.setIdVerbo(idVerbo);
				n.setIdVerboCompuesto(idVerboCompuesto);
				n.setJuego(juego);
				n.setModo(modo);
				n.setParticularidad(particularidad);
				n.setPats(pats);
				n.setPersona(persona);
				List<String> newPreps = new ArrayList<>();
				if (this.getPreposiciones() != null) {
						for (String prep : this.getPreposiciones()) {
								newPreps.add(prep);
						}
				}
				n.setPreposiciones(newPreps);
				n.setPropagacion(propagacion);
				n.setReduplicacion(reduplicacion);
				n.setRegEx(this.regEx);
				n.setSilaba(silaba);
				n.setSubPart(subPart);
				n.setTema(tema);
				n.setTemaPropuesto(temaPropuesto);
				n.setTerminacion(terminacion);
				n.setTiempo(tiempo);
				n.setTiempoJuego(tiempoJuego);
				n.setTipoDesinencia(tipoDesinencia);
				n.setTipoVerboExtendido(tipoVerboExtendido);
				n.setVoz(voz);
				n.setVozJuego(vozJuego);
				return n;
		}

}
