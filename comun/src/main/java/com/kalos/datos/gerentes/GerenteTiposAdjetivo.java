package com.kalos.datos.gerentes;

import java.util.List;
import java.util.Map;

import com.kalos.beans.TipoJerarquico;

public interface GerenteTiposAdjetivo extends Reseleccionable{
	
	/**
	 * selecciona todos los tipos de adjetivo
	 * @return
	 */
	List<TipoJerarquico> getTodos();
	
	/**
	 * mapa entre los tipos enteros y los ID
	 * @return
	 */
	Map<Integer, String> getMapaTiposID();
	
}
