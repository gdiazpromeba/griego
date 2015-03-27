package com.kalos.operaciones;

import java.util.Map;

import com.kalos.enumeraciones.EnuExplosiones;
import com.kalos.enumeraciones.ValorExplosion;

public interface IExplotadorPalabras {

	/**
	 * setea los valores de configuraciones, obtenidos del archivo de configuración
	 * @param valores
	 */
	public abstract void setValoresExplosiones(Map<EnuExplosiones, ValorExplosion> valores);


	/* (non-Javadoc)
	 * @see kalos.operaciones.IExplotadorPalabras#setPalabraOriginal(java.lang.String)
	 */
	public abstract void setPalabraOriginal(String palabraCompleta);

	/**
	 * dada una palabra, decide si, para ese tipo de explosión, la heuréstica indica que debo explotarla
	 * @param tipo
	 * @param palabra
	 * @return
	 */
	public abstract boolean deboExplotarPorheuristica(EnuExplosiones tipo,
			String palabra);

	/**
	 * devuelve la decisión de si debo explotar o no, basándose en los valores de 
	 * configuración y en la heuréstica
	 * @param cual
	 * @return
	 */
	public abstract boolean explotoONo(EnuExplosiones cual);

	/* (non-Javadoc)
	 * @see kalos.operaciones.IExplotadorPalabras#getExplotadas()
	 */
	public abstract String[] getExplotadas();

}