package kalos.beans;

import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.GradoComparacion;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.Particularidad;

public class IrrAdjetivoIndividual {
	private String id;
	private String adjetivoId;
	private Genero genero;
	private Particularidad particularidad;
	private Numero numero;
	private Caso caso;
	private GradoComparacion grado;
	private int subindice;
	private String forma;
	

	
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
}
