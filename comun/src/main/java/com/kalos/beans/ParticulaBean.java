package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.TipoPalabra;

public class ParticulaBean  implements ISignificados{

	private String particulaId;
	private TipoPalabra particulaTipo;
	private Particularidad particularidad;
	private Caso caso;
	private Persona persona;
	private int subindice;
	private Genero genero;
	private Numero numero;
	private String forma;
	private boolean muestraEnDiccionario;
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();
	private String particulaEncabezadoId;
	
	/**
	 * @return Returns the caso.
	 */
	public Caso getCaso() {
		return caso;
	}
	/**
	 * @param caso The caso to set.
	 */
	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	/**
	 * @return Returns the forma.
	 */
	public String getForma() {
		return forma;
	}
	/**
	 * @param forma The forma to set.
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}
	/**
	 * @return Returns the genero.
	 */
	public Genero getGenero() {
		return genero;
	}
	/**
	 * @param genero The genero to set.
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	/**
	 * @return Returns the numero.
	 */
	public Numero getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(Numero numero) {
		this.numero = numero;
	}
	/**
	 * @return Returns the particulaId.
	 */
	public String getParticulaId() {
		return particulaId;
	}
	/**
	 * @param particulaId The particulaId to set.
	 */
	public void setParticulaId(String particulaId) {
		this.particulaId = particulaId;
	}
	/**
	 * @return Returns the particularidad.
	 */
	public Particularidad getParticularidad() {
		return particularidad;
	}
	/**
	 * @param particularidad The particularidad to set.
	 */
	public void setParticularidad(Particularidad particularidad) {
		this.particularidad = particularidad;
	}
	/**
	 * @return Returns the particulaTipo.
	 */
	public TipoPalabra getParticulaTipo() {
		return particulaTipo;
	}
	/**
	 * @param particulaTipo The particulaTipo to set.
	 */
	public void setParticulaTipo(TipoPalabra particulaTipo) {
		this.particulaTipo = particulaTipo;
	}
	/**
	 * @return Returns the subindice.
	 */
	public int getSubindice() {
		return subindice;
	}
	/**
	 * @param subindice The subindice to set.
	 */
	public void setSubindice(int subindice) {
		this.subindice = subindice;
	}
	/* (non-Javadoc)
	 * @see com.kalos.beans.ISignificados#getId()
	 */
	public String getId() {
		return this.particulaId;
	}
	/* (non-Javadoc)
	 * @see com.kalos.beans.ISignificados#getSignificados()
	 */
	public Map<Idioma, Significado> getSignificados() {
		return significados;
	}
	/* (non-Javadoc)
	 * @see com.kalos.beans.ISignificados#setSignificados(java.util.Map)
	 */
	public void setSignificados(Map<Idioma, Significado> map) {
		this.significados=map;
	}
	/**
	 * @return Returns the persona.
	 */
	public Persona getPersona() {
		return persona;
	}
	/**
	 * @param persona The persona to set.
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	/**
	 * @return the particulaEncabezadoId
	 */
	public String getParticulaEncabezadoId() {
		return particulaEncabezadoId;
	}
	/**
	 * @param particulaEncabezadoId the particulaEncabezadoId to set
	 */
	public void setParticulaEncabezadoId(String particulaEncabezadoId) {
		this.particulaEncabezadoId = particulaEncabezadoId;
	}
    
    public boolean isMuestraEnDiccionario() {
        return muestraEnDiccionario;
    }
    
    public void setMuestraEnDiccionario(boolean muestraEnDiccionario) {
        this.muestraEnDiccionario = muestraEnDiccionario;
    }

}
