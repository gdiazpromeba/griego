package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;

/**
 * Una entrada de diccionario de adjetivos
 * @author gdiaz
 *
 */
public class VerboBean implements ISignificados{
	private String id;
	private String letra;
	private int codigo;
	private String verbo;
	private String sufijo;
	private Particularidad particularidad;
	private int tipoVerbo;
	private String idTipo;	
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();
	private boolean dibujado;
	private boolean compuesto;
	
	
	/**
	 * @param significados The significados to set.
	 */
	public void setSignificados(Map<Idioma, Significado> significados) {
		this.significados = significados;
	}
	

	/**
	 * @return Returns the codigo.
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the compuesto.
	 */
	public boolean isCompuesto() {
		return compuesto;
	}

	/**
	 * @param compuesto The compuesto to set.
	 */
	public void setCompuesto(boolean compuesto) {
		this.compuesto = compuesto;
	}

	/**
	 * @return Returns the dibujado.
	 */
	public boolean isDibujado() {
		return dibujado;
	}

	/**
	 * @param dibujado The dibujado to set.
	 */
	public void setDibujado(boolean dibujado) {
		this.dibujado = dibujado;
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
	 * @return Returns the idTipo.
	 */
	public String getIdTipo() {
		return idTipo;
	}

	/**
	 * @param idTipo The idTipo to set.
	 */
	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	/**
	 * @return Returns the letra.
	 */
	public String getLetra() {
		return letra;
	}

	/**
	 * @param letra The letra to set.
	 */
	public void setLetra(String letra) {
		this.letra = letra;
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
	 * @return Returns the sufijo.
	 */
	public String getSufijo() {
		return sufijo;
	}

	/**
	 * @param sufijo The sufijo to set.
	 */
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	/**
	 * @return Returns the tipoVerbo.
	 */
	public int getTipoVerbo() {
		return tipoVerbo;
	}

	/**
	 * @param tipoVerbo The tipoVerbo to set.
	 */
	public void setTipoVerbo(int tipoVerbo) {
		this.tipoVerbo = tipoVerbo;
	}

	/**
	 * @return Returns the verbo.
	 */
	public String getVerbo() {
		return verbo;
	}

	/**
	 * @param verbo The verbo to set.
	 */
	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}

	/**
	 * @return Returns the significados.
	 */
	public Map<Idioma, Significado> getSignificados() {
		return significados;
	}



}
