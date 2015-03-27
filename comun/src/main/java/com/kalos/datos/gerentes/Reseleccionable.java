package com.kalos.datos.gerentes;

import java.util.List;

/**
 * Esta interfaz indica que se puede llamar al método "reseleccionar"
 * y la clase que la implemente debe guardar agún tipo de referencia al
 * último query que fue invocado, junto con sus parámetros
 * @author gdiaz
 *
 */
public interface Reseleccionable {
	
	/**
	 * rehace el último query, con los parámetros usados la última vez
	 * (útil para reflejar cambios después de una adición, borrado, etc).
	 * @return
	 */
	List<?> reseleccionar();
	
	
	/**
	 * me obliga a tener un miembro interno con los últimos parámetros
	 * @param parametros
	 */
	void setUltimosParametros(Object[] parametros);
	
	/**
	 * me obliga a tener un miembro interno con los últimos parámetros
	 */
	Object[] getUltimosParametros();
	
	/**
	 * me obliga a mantener algún tipo de referencia (como el nombre)
	 * al último query utilizado
	 * @return
	 */
	String getUltimaSeleccion();

	/**
	 * me obliga a mantener algún tipo de referencia (como el nombre)
	 * al último query utilizado
	 * @return
	 */
	void setUltimaSeleccion(String query);

	
	

}
