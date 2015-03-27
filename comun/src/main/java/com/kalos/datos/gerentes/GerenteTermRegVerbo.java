package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TermRegVerbo;

public interface GerenteTermRegVerbo {

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTiposVerbo#getTodos()
	 */
	List<TermRegVerbo> seleccionaPorTerminacion(String terminacion);

}