package com.kalos.datos.gerentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.TiposVerboDAO;

public class GerenteTiposVerboImpl implements GerenteTiposVerbo {

	private TiposVerboDAO tiposVerboDAO;
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTiposVerbo#getTodos()
	 */
	public List<TipoJerarquico> getTodos() {
		List<TipoJerarquico> tipos=tiposVerboDAO.getTodos();
		return tipos;
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTiposVerbo#getMapaTiposID()
	 */
	public Map<Integer, String> getMapaTiposID(){
		List<TipoJerarquico> tipos=tiposVerboDAO.getTodos();
		Map<Integer, String> resultado=new HashMap<Integer, String>();
		for (TipoJerarquico tij: tipos){
			resultado.put(tij.getValorEntero(), tij.getId());
		}
		return  resultado;
	}
	


	

	/**
	 * @return Returns the tiposVerboDAO.
	 */
	public TiposVerboDAO getTiposVerboDAO() {
		return tiposVerboDAO;
	}

	/**
	 * @param tiposVerboDAO The tiposVerboDAO to set.
	 */
	public void setTiposVerboDAO(TiposVerboDAO tiposVerboDAO) {
		this.tiposVerboDAO = tiposVerboDAO;
	}

}
