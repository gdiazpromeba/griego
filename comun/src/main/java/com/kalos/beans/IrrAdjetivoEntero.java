package com.kalos.beans;

import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Particularidad;

public class IrrAdjetivoEntero {
	private String id;
	private String adjetivoId;
	private Genero genero;
	private Particularidad particularidad;
	private int subindice;
	private String nominativo;
	private String genitivo;
	private GradoComparacion grado;
	private String tipoSustantivoId;
	private int tipoSustantivo;
	private boolean soloSingular;

	
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
	 * @return Returns the genitivo.
	 */
	public String getGenitivo() {
		return genitivo;
	}
	/**
	 * @param genitivo The genitivo to set.
	 */
	public void setGenitivo(String genitivo) {
		this.genitivo = genitivo;
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
	 * @return Returns the nominativo.
	 */
	public String getNominativo() {
		return nominativo;
	}
	/**
	 * @param nominativo The nominativo to set.
	 */
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
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
	 * @return Returns the tipoSustantivoId.
	 */
	public String getTipoSustantivoId() {
		return tipoSustantivoId;
	}
	/**
	 * @param tipoSustantivoId The tipoSustantivoId to set.
	 */
	public void setTipoSustantivoId(String tipoSustantivoId) {
		this.tipoSustantivoId = tipoSustantivoId;
	}
	/**
	 * @return Returns the adjetivoId.
	 */
	public String getAdjetivoId() {
		return adjetivoId;
	}
	/**
	 * @param adjetivoId The adjetivoId to set.
	 */
	public void setAdjetivoId(String adjetivoId) {
		this.adjetivoId = adjetivoId;
	}
	/**
	 * @return Returns the tipoSustantivo.
	 */
	public int getTipoSustantivo() {
		return tipoSustantivo;
	}
	/**
	 * @param tipoSustantivo The tipoSustantivo to set.
	 */
	public void setTipoSustantivo(int tipoSustantivo) {
		this.tipoSustantivo = tipoSustantivo;
	}
	/**
	 * @return Returns the grado.
	 */
	public GradoComparacion getGrado() {
		return grado;
	}
	/**
	 * @param grado The grado to set.
	 */
	public void setGrado(GradoComparacion grado) {
		this.grado = grado;
	}
    public boolean isSoloSingular() {
        return this.soloSingular;
    }
    public void setSoloSingular(boolean soloSingular) {
        this.soloSingular = soloSingular;
    }
}
