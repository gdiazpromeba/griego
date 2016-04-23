package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.DesinVerbo;
import com.kalos.datos.dao.DesinVerbosDAO;

public class GerenteDesinVerbosImpl implements GerenteDesinVerbos {
	
	private DesinVerbosDAO desinVerbosDAO;
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteDesinVerbos#seleccionaTodasOrdenNoNulo()
	 */
	public List<DesinVerbo> seleccionaTodas(){
		return desinVerbosDAO.seleccionaTodasOrdenNoNulo();
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteDesinVerbos#seleccionaRestringidas()
	 */
	public List<DesinVerbo> seleccionaRestringidas(){
		return desinVerbosDAO.seleccionaRestringidas();
	}

	public void setDesinVerbosDAO(DesinVerbosDAO desinVerbosDAO) {
		this.desinVerbosDAO = desinVerbosDAO;
	}


}
