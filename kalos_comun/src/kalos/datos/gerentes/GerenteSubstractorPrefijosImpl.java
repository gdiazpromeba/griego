package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.SubstractorPrefijosBean;
import kalos.datos.dao.SubstractorPrefijosDAO;

public class GerenteSubstractorPrefijosImpl implements GerenteSubstractorPrefijos {

	private SubstractorPrefijosDAO substractorPrefijosDAO;
	
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteSubstractorPrefijos#seleccionaPorForma(java.lang.String)
	 */
	public List<SubstractorPrefijosBean> seleccionaPorForma(String forma) {
		return substractorPrefijosDAO.seleccionaPorForma(forma);
	}


	/**
	 * @param substractorPrefijosDAO The substractorPrefijosDAO to set.
	 */
	public void setSubstractorPrefijosDAO(SubstractorPrefijosDAO substractorPrefijosDAO) {
		this.substractorPrefijosDAO = substractorPrefijosDAO;
	}
}
