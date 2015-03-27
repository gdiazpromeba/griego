package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TemaTermRegNominal;
import com.kalos.datos.dao.TemasTermRegNominalDAO;

public class GerenteTemasTermRegNominalImpl implements GerenteTemasTermRegNominal {

	private TemasTermRegNominalDAO temasTermRegNominalDAO;
	
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTemasTermRegNominal#seleccionaTodos()
	 */
	public List<TemaTermRegNominal> seleccionaTodos(){
		return temasTermRegNominalDAO.seleccionaTodos();
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTemasTermRegNominal#borra()
	 */
	public void borra(){
		temasTermRegNominalDAO.borra();
	}
	

	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTemasTermRegNominal#inserta(kalos.beans.TemaTermRegNominal)
	 */
	public void inserta(TemaTermRegNominal ttrn){
		temasTermRegNominalDAO.inserta(ttrn);
	}

	/**
	 * @param temasTermRegNominalDAO The temasTermRegNominalDAO to set.
	 */
	public void setTemasTermRegNominalDAO(TemasTermRegNominalDAO temasTermRegNominalDAO) {
		this.temasTermRegNominalDAO = temasTermRegNominalDAO;
	}

}