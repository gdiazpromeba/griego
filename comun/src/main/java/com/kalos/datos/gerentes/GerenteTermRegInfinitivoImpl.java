package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TermRegInfinitivo;
import com.kalos.datos.dao.TermRegInfinitivoDAO;

public class GerenteTermRegInfinitivoImpl implements GerenteTermRegInfinitivo   {

	
	private TermRegInfinitivoDAO termRegInfinitivoDAO;

	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTermRegInfinitivo#seleccionaPorTerminacion(java.lang.String)
	 */
	public List<TermRegInfinitivo> seleccionaPorTerminacion(String terminacion) {
		List<TermRegInfinitivo> resultado= termRegInfinitivoDAO.seleccionaPorTerminacion(terminacion);
		return resultado;
	}

	
	public void inserta(TermRegInfinitivo bean){
		termRegInfinitivoDAO.inserta(bean);
	}
	
	public void borraTodo(){
		termRegInfinitivoDAO.borraTodo();
	}

	/**
	 * @return Returns the termRegInfinitivoDAO.
	 */
	public TermRegInfinitivoDAO getTermRegInfinitivoDAO() {
		return termRegInfinitivoDAO;
	}


	/**
	 * @param termRegInfinitivoDAO The termRegInfinitivoDAO to set.
	 */
	public void setTermRegInfinitivoDAO(TermRegInfinitivoDAO termRegInfinitivoDAO) {
		this.termRegInfinitivoDAO = termRegInfinitivoDAO;
	}


	
}
