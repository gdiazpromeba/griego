package com.kalos.datos.gerentes;

import java.util.List;
import java.util.Map;

import com.kalos.beans.TipoJerarquico;
import com.kalos.beans.TipoSustantivo;

public interface GerenteTiposSustantivo {
	
	/**
	 * selecciona todos los tipos de sustantivo
	 * @return
	 */
	List<TipoSustantivo> getTodos();
	
	
	/**
	 * selecciona todos los tipos hoja que tienen al parámetro como padre 
	 * @param tipoSustantivo
	 * @return
	 */
	List<TipoJerarquico> getTiposHoja(String tipoSustantivoId);
	
	
	/**
	 * mapa entre los tipos enteros y los ID
	 * @return
	 */
	Map<Integer, String> getMapaTiposID();
    
    TipoSustantivo seleccionIndividual(String id);
    
	/**
	 * dice si un tipo nominal entero es pluralizado. 
	 * La primera vez carga un mapa, que luego conserva.
	 * @param tipo
	 * @return
	 */
	public boolean esPluralizado(int tipo);

	
}
