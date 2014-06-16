package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.CubosTipoPartBean;
import kalos.datos.dao.CubosTipoPartDAO;

public class GerenteCubosTipoPartImpl implements GerenteCubosTipoPart  {
	
	private CubosTipoPartDAO cubosTipoPartDAO;

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteCubosTipoPart#seleccionaTodos()
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
