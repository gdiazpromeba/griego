package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.DesinInfinitivo;

public interface DesinInfinitivosDAO {

	/**
	 * selecciona las desinencias actuales de trabajo, según la configuración 
	 * (es decir, aquéllas que no tienen el orden nulo)
	 * @return
	 */
	public abstract List<DesinInfinitivo> seleccionaRestringidas();

	/**
	 * selecciona todas las desinencias  
	 * (para generadores y AM)
	 * @return
	 */
	public abstract List<DesinInfinitivo> seleccionaTodas();

	void inserta(DesinInfinitivo desinInfinitivo);

}