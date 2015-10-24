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
    

	public Aumento getAumento() {
		return aumento;
	}

	public void setAumento(Aumento s) {
		aumento = s;
	}

	public Contraccion getContraccion() {
		return contraccion;
	}

	public void setContraccion(Contraccion c) {
		contraccion = c;
	}

	public FuerteDebil getFuerte() {
		return fuerte;
	}

	public void setFuerte(FuerteDebil p) {
		fuerte = p;
	}

	public String getId() {
		return id;
	}

	public void setId(String s) {
		id = s;
	}

	public int getJuego() {
		return juego;
	}

	public void setJuego(int i) {
		juego = i;
	}

	public Modo getModo() {
		return modo;
	}

	public void setModo(Modo p) {
		modo = p;
	}


	public Propagacion getPropagacion() {
		return propagacion;
	}

	public void setPropagacion(Propagacion d) {
		propagacion = d;
	}

	public boolean isReduplicacion() {
		return reduplicacion;
	}

	public void setReduplicacion(boolean flag) {
		reduplicacion = flag;
	}

	public int getSubPart() {
		return subPart;
	}

	public void setSubPart(int i) {
		subPart = i;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String s) {
		tema = s;
	}

	public Tiempo getTiempo() {
		return tiempo;
	}

	public void setTiempo(Tiempo j) {
		tiempo = j;
	}

	public Tiempo getTiempoJuego() {
		return tiempoJuego;
	}

	public void setTiempoJuego(Tiempo j) {
		tiempoJuego = j;
	}



	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz z) {
		voz = z;
	}

	public Voz getVozJuego() {
		return vozJuego;
	}

	public void setVozJuego(Voz z) {
		vozJuego = z;
	}

	public boolean isCompuesto() {
		return compuesto;
	}

	public void setCompuesto(boolean flag) {
		compuesto = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acento == null) ? 0 : acento.hashCode());
		result = prime * result + ((aumento == null) ? 0 : aumento.hashCode());
		result = prime * result + (compuesto ? 1231 : 1237);
		result = prime * result
				+ ((contraccion == null) ? 0 : contraccion.hashCode());
		result = prime
				* result
				+ ((formaADestransformar == null) ? 0 : formaADestransformar
						.hashCode());
		result = prime
				* result
				+ ((formaDestransformada == null) ? 0 : formaDestransformada
						.hashCode());
		result = prime * result
				+ ((formaOriginal == null) ? 0 : formaOriginal.hashCode());
		result = prime
				* result
				+ ((formaOriginalCompuesta == null) ? 0
						: formaOriginalCompuesta.hashCode());
		result = prime * result + ((fuerte == null) ? 0 : fuerte.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idVerbo == null) ? 0 : idVerbo.hashCode());
		result = prime
				* result
				+ ((idVerboCompuesto == null) ? 0 : idVerboCompuesto.hashCode());
		result = prime * result + juego;
		result = prime * result + ((modo == null) ? 0 : modo.hashCode());
		result = prime * result
				+ ((particularidad == null) ? 0 : particularidad.hashCode());
		result = prime * result + (pats ? 1231 : 1237);
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result
				+ ((preposiciones == null) ? 0 : preposiciones.hashCode());
		result = prime * result
				+ ((propagacion == null) ? 0 : propagacion.hashCode());
		result = prime * result + (reduplicacion ? 1231 : 1237);
		result = prime * result + ((regEx == null) ? 0 : regEx.hashCode());
		result = prime * result + ((silaba == null) ? 0 : silaba.hashCode());
		result = prime * result + subPart;
		result = prime * result + ((tema == null) ? 0 : tema.hashCode());
		result = prime * result
				+ ((temaPropuesto == null) ? 0 : temaPropuesto.hashCode());
		result = prime * result
				+ ((terminacion == null) ? 0 : terminacion.hashCode());
		result = prime * result + ((tiempo == null) ? 0 : tiempo.hashCode());
		result = prime * result
				+ ((tiempoJuego == null) ? 0 : tiempoJuego.hashCode());
		result = prime * result + tipoDesinencia;
		result = prime * result + tipoVerboExtendido;
		result = prime * result + ((voz == null) ? 0 : voz.hashCode());
		result = prime * result
				+ ((vozJuego == null) ? 0 : vozJuego.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TermRegVerbo other = (TermRegVerbo) obj;
		if (acento != other.acento)
			return false;
		if (aumento != other.aumento)
			return false;
		if (compuesto != other.compuesto)
			return false;
		if (contraccion != other.contraccion)
			return false;
		if (formaADestransformar == null) {
			if (other.formaADestransformar != null)
				return false;
		} else if (!formaADestransformar.equals(other.formaADestransformar))
			return false;
		if (formaDestransformada == null) {
			if (other.formaDestransformada != null)
				return false;
		} else if (!formaDestransformada.equals(other.formaDestransformada))
			return false;
		if (formaOriginal == null) {
			if (other.formaOriginal != null)
				return false;
		} else if (!formaOriginal.equals(other.formaOriginal))
			return false;
		if (formaOriginalCompuesta == null) {
			if (other.formaOriginalCompuesta != null)
				return false;
		} else if (!formaOriginalCompuesta.equals(other.formaOriginalCompuesta))
			return false;
		if (fuerte != other.fuerte)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idVerbo == null) {
			if (other.idVerbo != null)
				return false;
		} else if (!idVerbo.equals(other.idVerbo))
			return false;
		if (idVerboCompuesto == null) {
			if (other.idVerboCompuesto != null)
				return false;
		} else if (!idVerboCompuesto.equals(other.idVerboCompuesto))
			return false;
		if (juego != other.juego)
			return false;
		if (modo != other.modo)
			return false;
		if (particularidad != other.particularidad)
			return false;
		if (pats != other.pats)
			return false;
		if (persona != other.persona)
			return false;
		if (preposiciones == null) {
			if (other.preposiciones != null)
				return false;
		} else if (!preposiciones.equals(other.preposiciones))
			return false;
		if (propagacion != other.propagacion)
			return false;
		if (reduplicacion != other.reduplicacion)
			return false;
		if (regEx == null) {
			if (other.regEx != null)
				return false;
		} else if (!regEx.equals(other.regEx))
			return false;
		if (silaba != other.silaba)
			return false;
		if (subPart != other.subPart)
			return false;
		if (tema == null) {
			if (other.tema != null)
				return false;
		} else if (!tema.equals(other.tema))
			return false;
		if (temaPropuesto == null) {
			if (other.temaPropuesto != null)
				return false;
		} else if (!temaPropuesto.equals(other.temaPropuesto))
			return false;
		if (terminacion == null) {
			if (other.terminacion != null)
				return false;
		} else if (!terminacion.equals(other.terminacion))
			return false;
		if (tiempo != other.tiempo)
			return false;
		if (tiempoJuego != other.tiempoJuego)
			return false;
		if (tipoDesinencia != other.tipoDesinencia)
			return false;
		if (tipoVerboExtendido != other.tipoVerboExtendido)
			return false;
		if (voz != other.voz)
			return false;
		if (vozJuego != other.vozJuego)
			return false;
		return true;
	}

	public boolean isPats() {
		return pats;
	}

	public void setPats(boolean flag) {
		pats = flag;
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
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getRegEx() {
		return regEx;
	}

	public void setRegEx(String regex) {
		this.regEx = regex;
	}

	public Silaba getSilaba() {
		return silaba;
	}

	public void setSilaba(Silaba silaba) {
		this.silaba = silaba;
	}

	public Acento getAcento() {
		return acento;
	}

	public void setAcento(Acento acento) {
		this.acento = acento;
	}

	private String regEx;
	private Silaba silaba;
	private Acento acento;

	public String getIdVerboCompuesto() {
		return idVerboCompuesto;
	}

	public void setIdVerboCompuesto(String idVerboCompuesto) {
		this.idVerboCompuesto = idVerboCompuesto;
	}

	public Particularidad getParticularidad() {
		return particularidad;
	}

	public void setParticularidad(Particularidad particularidad) {
		this.particularidad = particularidad;
	}

	public String getIdVerbo() {
		return idVerbo;
	}

	public void setIdVerbo(String idVerbo) {
		this.idVerbo = idVerbo;
	}

	public String getFormaOriginal() {
		return formaOriginal;
	}

	public void setFormaOriginal(String formaOriginal) {
		this.formaOriginal = formaOriginal;
	}

	public String getFormaOriginalCompuesta() {
		return formaOriginalCompuesta;
	}

	public void setFormaOriginalCompuesta(String formaOriginalCompuesta) {
		this.formaOriginalCompuesta = formaOriginalCompuesta;
	}

	public TiempoOAspecto getTiempoOAspecto() {
		return tiempo;
	}

	public List<String> getPreposiciones() {
		return preposiciones;
	}

	public void setPreposiciones(List<String> preposiciones) {
		this.preposiciones = preposiciones;
	}

	public String getFormaADestransformar() {
		return formaADestransformar;
	}

	public void setFormaADestransformar(String formaADestransformar) {
		this.formaADestransformar = formaADestransformar;
	}

	public String getTerminacion() {
		return terminacion;
	}

	public void setTerminacion(String terminacion) {
		this.terminacion = terminacion;
	}

	public int getTipoVerboExtendido() {
		return tipoVerboExtendido;
	}

	public void setTipoVerboExtendido(int tipoVerboExtendido) {
		this.tipoVerboExtendido = tipoVerboExtendido;
	}

	public String getFormaDestransformada() {
		return formaDestransformada;
	}

	public void setFormaDestransformada(String formaDestransformada) {
		this.formaDestransformada = formaDestransformada;
	}

	public int getTipoDesinencia() {
		return tipoDesinencia;
	}

	public void setTipoDesinencia(int tipoDesinencia) {
		this.tipoDesinencia = tipoDesinencia;
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
        if (this.getPreposiciones()!=null){
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
