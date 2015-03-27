package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TransParticipiosBean;
import com.kalos.datos.dao.TransParticipiosDAO;

public class GerenteTransParticipiosImpl implements GerenteTransParticipios {

	private TransParticipiosDAO transParticipiosDAO;
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTransParticipios#seleccionaTodos()
	 */
	public List<TransParticipiosBean> seleccionaTodos(){
		return transParticipiosDAO.getTodos();
	}
	
	public List<TransParticipiosBean> seleccionaPorSubstraer(String forma){
		return transParticipiosDAO.seleccionaPorSubstraer(forma);
	}

	/**
	 * @param transParticipiosDAO The transParticipiosDAO to set.
	 */
	public void setTransParticipiosDAO(TransParticipiosDAO transParticipiosDAO) {
		this.transParticipiosDAO = transParticipiosDAO;
	}
	
}
