package kalos.datos.dao;

import java.util.List;

import kalos.beans.TermRegInfinitivo;

public interface TermRegInfinitivoDAO {

	/* (non-Javadoc)
	 * @see kalos.dao.TermRegInfinitivoDAO#seleccionaPorTerminacion(java.lang.String)
	 */
	List<TermRegInfinitivo> seleccionaPorTerminacion(String terminacion);
	
	
	void inserta(TermRegInfinitivo bean);
	
	void borraTodo();

}