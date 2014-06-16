package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TermRegVerbo;
import kalos.datos.dao.TermRegVerboDAO;

public class GerenteTermRegVerboImpl implements GerenteTermRegVerbo  {

	
	private TermRegVerboDAO termRegVerboDAO;

	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTiposVerbo#getTodos()
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTermRegVerbo#seleccionaPorTerminacion(java.lang.String)
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
