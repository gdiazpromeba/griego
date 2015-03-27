package com.kalos.beans;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Voz;

/**
 * Bean de irregularidad verbal
 * 
 * @author Manuela
 * 
 */
public class IrrInfinitivoBean {

	private String id;

	private String verboId;

	private Particularidad partic;

	private int supPart;

	private Aspecto aspecto;

	private Voz voz;

	private FuerteDebil fuerte;

	private String forma;

	/**
	 * @return Returns the aspecto.
	 */
	public Aspecto getAspecto() {
		return aspecto;
	}

	/**
	 * @param aspecto The aspecto to set.
	 */
	public void setAspecto(Aspecto aspecto) {
		this.aspecto = aspecto;
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
	 * @return Returns the fuerte.
	 */
	public FuerteDebil getFuerte() {
		return fuerte;
	}

	/**
	 * @param fuerte The fuerte to set.
	 */
	public void setFuerte(FuerteDebil fuerte) {
		this.fuerte = fuerte;
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
	 * @return Returns the partic.
	 */
	public Particularidad getPartic() {
		return partic;
	}

	/**
	 * @param partic The partic to set.
	 */
	public void setPartic(Particularidad partic) {
		this.partic = partic;
	}

	/**
	 * @return Returns the supPart.
	 */
	public int getSupPart() {
		return supPart;
	}

	/**
	 * @param supPart The supPart to set.
	 */
	public void setSupPart(int supPart) {
		this.supPart = supPart;
	}

	/**
	 * @return Returns the verboId.
	 */
	public String getVerboId() {
		return verboId;
	}

	/**
	 * @param verboId The verboId to set.
	 */
	public void setVerboId(String verboId) {
		this.verboId = verboId;
	}

	/**
	 * @return Returns the voz.
	 */
	public Voz getVoz() {
		return voz;
	}

	/**
	 * @param voz The voz to set.
	 */
	public void setVoz(Voz voz) {
		this.voz = voz;
	}
}
