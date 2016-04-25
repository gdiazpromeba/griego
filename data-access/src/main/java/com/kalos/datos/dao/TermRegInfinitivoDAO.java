package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TermRegInfinitivo;

public interface TermRegInfinitivoDAO {

	/* (non-Javadoc)
	 * @see kalos.dao.TermRegInfinitivoDAO#seleccionaPorTerminacion(java.lang.String)
	 */
	List<TermRegInfinitivo> seleccionaPorTerminacion(String terminacion);

	public List<TermRegInfinitivo> seleccionaTodo();
	
	void inserta(TermRegInfinitivo bean);
	
	void borraTodo();

}