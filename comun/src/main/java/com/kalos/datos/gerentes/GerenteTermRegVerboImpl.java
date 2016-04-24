package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TermRegVerbo;
import com.kalos.datos.dao.TermRegVerboDAO;

public class GerenteTermRegVerboImpl implements GerenteTermRegVerbo  {

	
	private TermRegVerboDAO termRegVerboDAO;

	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTiposVerbo#seleccionaTodo()
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTermRegVerbo#seleccionaPorTerminacion(java.lang.String)
	 */
	public List<TermRegVerbo> seleccionaPorTerminacion(String terminacion) {
		List<TermRegVerbo> resultado= termRegVerboDAO.seleccionaPorTerminacion(terminacion);
		return resultado;
	}


	/**
	 * @return Returns the termRegVerboDAO.
	 */
	public TermRegVerboDAO getTermRegVerboDAO() {
		return termRegVerboDAO;
	}


	/**
	 * @param termRegVerboDAO The termRegVerboDAO to set.
	 */
	public void setTermRegVerboDAO(TermRegVerboDAO termRegVerboDAO) {
		this.termRegVerboDAO = termRegVerboDAO;
	}
	
}
