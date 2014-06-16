package kalos.beans;

import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.TipoPalabra;

/**
 * La representación visual para el usuario de un ítem del resultado de un análisis morfológico
 * @author gdiaz
 *
 */
public class ResultadoAMUsuario {

	private String id;
	private TipoPalabra tipoPalabra;
	private String formaCanonica;
	private String accidentes;
	private String significado;
	private String formaOriginal;
	private String formaOriginalBeta;
	private String formaCompleta;
	private Particularidad particularidad;
		
	/**
	 * @return Returns the accidentes.
	 */
	public String getAccidentes() {
		return accidentes;
	}
	/**
	 * @param accidentes The accidentes to set.
	 */
	public void setAccidentes(String accidentes) {
		this.accidentes = accidentes;
	}

	/**
	 * @return Returns the formaCanonica.
	 */
	public String getFormaCanonica() {
		return formaCanonica;
	}
	/**
	 * @param formaCanonica The formaCanonica to set.
	 */
	public void setFormaCanonica(String formaCanonica) {
		this.formaCanonica = formaCanonica;
	}
	/**
	 * @return Returns the formaCompleta.
	 */
	public String getFormaCompleta() {
		return formaCompleta;
	}
	/**
	 * @param formaCompleta The formaCompleta to set.
	 */
	public void setFormaCompleta(String formaCompleta) {
		this.formaCompleta = formaCompleta;
	}
	/**
	 * @return Returns the formaOriginal.
	 */
	public String getFormaOriginal() {
		return formaOriginal;
	}
	/**
	 * @param formaOriginal The formaOriginal to set.
	 */
	public void setFormaOriginal(String formaOriginal) {
		this.formaOriginal = formaOriginal;
	}
	/**
	 * @return Returns the formaOriginalBeta.
	 */
	public String getFormaOriginalBeta() {
		return formaOriginalBeta;
	}
	/**
	 * @param formaOriginalBeta The formaOriginalBeta to set.
	 */
	public void setFormaOriginalBeta(String formaOriginalBeta) {
		this.formaOriginalBeta = formaOriginalBeta;
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
	 * @return Returns the particularidadCanonica.
	 */
	
	/**
	 * @return Returns the significado.
	 */
	public String getSignificado() {
		return significado;
	}
	/**
	 * @param significado The significado to set.
	 */
	public void setSignificado(String significado) {
		this.significado = significado;
	}
	/**
	 * @return Returns the tipoPalabra.
	 */
	public TipoPalabra getTipoPalabra() {
		return tipoPalabra;
	}
	/**
	 * @param tipoPalabra The tipoPalabra to set.
	 */
	public void setTipoPalabra(TipoPalabra tipoPalabra) {
		this.tipoPalabra = tipoPalabra;
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
	
	

}
