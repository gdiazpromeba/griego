package com.kalos.beans;

import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;

/**
 * interfa que permite hacer algunas cosas en común a las con las irregularidaes enteras y las individuales
 * @author Manuela
 *
 */
public interface IBeanIrrVerbo {

	/**
	 * @return Returns the fuerte.
	 */
	public abstract FuerteDebil getFuerte();

	/**
	 * @param fuerte
	 *            The fuerte to set.
	 */
	public abstract void setFuerte(FuerteDebil fuerte);

	/**
	 * @return Returns the id.
	 */
	public abstract String getId();

	/**
	 * @param id
	 *            The id to set.
	 */
	public abstract void setId(String id);

	/**
	 * @return Returns the modo.
	 */
	public abstract Modo getModo();

	/**
	 * @param modo
	 *            The modo to set.
	 */
	public abstract void setModo(Modo modo);

	/**
	 * @return Returns the partic.
	 */
	public abstract Particularidad getPartic();

	/**
	 * @param partic
	 *            The partic to set.
	 */
	public abstract void setPartic(Particularidad partic);

	/**
	 * @return Returns the subPart.
	 */
	public abstract int getSubPart();

	/**
	 * @param subPart
	 *            The subPart to set.
	 */
	public abstract void setSubPart(int subPart);

	/**
	 * @return Returns the tiempo.
	 */
	public abstract Tiempo getTiempo();

	/**
	 * @param tiempo
	 *            The tiempo to set.
	 */
	public abstract void setTiempo(Tiempo tiempo);

	/**
	 * @return Returns the verboId.
	 */
	public abstract String getVerboId();

	/**
	 * @param verboId
	 *            The verboId to set.
	 */
	public abstract void setVerboId(String verboId);

	/**
	 * @return Returns the voz.
	 */
	public abstract Voz getVoz();

	/**
	 * @param voz The voz to set.
	 */
	public abstract void setVoz(Voz voz);
	
	boolean isCompuesto();
	
	void setCompuesto(boolean value);
	
	



}