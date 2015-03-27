package com.kalos.beans;

import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Particularidad;

public class AdjetivoComoNominalBean implements NomGenTipo {

	private String idAdjetivo;
	private String nominativo;
	private String genitivo;
	private int tipoNominal; 
	private Genero genero;
	private Particularidad particularidad;
	private int subindice;
    private GradoComparacion grado;
	
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
	/* (non-Javadoc)
	 * @see com.kalos.beans.NomGenTipo#getGenitivo()
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
	 * @return Returns the idAdjetivo.
	 */
	public String getIdAdjetivo() {
		return idAdjetivo;
	}
	/**
	 * @param idAdjetivo The idAdjetivo to set.
	 */
	public void setIdAdjetivo(String idAdjetivo) {
		this.idAdjetivo = idAdjetivo;
	}
	/* (non-Javadoc)
	 * @see com.kalos.beans.NomGenTipo#getNominativo()
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
	/* (non-Javadoc)
	 * @see com.kalos.beans.NomGenTipo#getTipoNominal()
	 */
	public int getTipoNominal() {
		return tipoNominal;
	}
	/**
	 * @param tipoNominal The tipoNominal to set.
	 */
	public void setTipoNominal(int tipoNominal) {
		this.tipoNominal = tipoNominal;
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
    public GradoComparacion getGrado() {
        return grado;
    }
    public void setGrado(GradoComparacion grado) {
        this.grado = grado;
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
}
