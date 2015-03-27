package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.CubosTipoPartBean;
import com.kalos.datos.dao.CubosTipoPartDAO;

public class GerenteCubosTipoPartImpl implements GerenteCubosTipoPart  {
	
	private CubosTipoPartDAO cubosTipoPartDAO;

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteCubosTipoPart#seleccionaTodos()
	 */
	public List<CubosTipoPartBean> seleccionaTodos(){
		return cubosTipoPartDAO.seleccionaTodos();
	}

	/**
	 * @param cubosTipoPartDAO The cubosTipoPartDAO to set.
	 */
	public void setCubosTipoPartDAO(CubosTipoPartDAO cubosTipoPartDAO) {
		this.cubosTipoPartDAO = cubosTipoPartDAO;
	}
}
