package com.kalos.beans;

import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;

public class IrrSustantivoBean {
	private String id;
	private String sustantivoId;
	private Particularidad particularidad;
	private int subindice;
	private Caso caso;
	private Numero numero;
	private String forma;
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
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
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
	/**
	 * @return Returns the sustantivoId.
	 */
	public String getSustantivoId() {
		return sustantivoId;
	}
	/**
	 * @param sustantivoId The sustantivoId to set.
	 */
	public void setSustantivoId(String sustantivoId) {
		this.sustantivoId = sustantivoId;
	}
	
	

}
