package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TermRegVerbo;

public interface GerenteTermRegVerbo {

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTiposVerbo#getTodos()
	 */
	List<TermRegVerbo> seleccionaPorTerminacion(String terminacion);

}