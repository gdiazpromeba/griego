package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.IrrInfinitivoBean;
import com.kalos.datos.dao.IrrInfinitivosDAO;
import com.kalos.enumeraciones.Particularidad;

public class GerenteIrrInfinitivosImpl implements GerenteIrrInfinitivos {
	
	IrrInfinitivosDAO irrInfinitivosDAO;

	/**
	 * @return Returns the irrInfinitivosDAO.
	 */
	public IrrInfinitivosDAO getIrrInfinitivosDAO() {
		return irrInfinitivosDAO;
	}

	/**
	 * @param irrInfinitivosDAO The irrInfinitivosDAO to set.
	 */
	public void setIrrInfinitivosDAO(IrrInfinitivosDAO irrInfinitivosDAO) {
		this.irrInfinitivosDAO = irrInfinitivosDAO;
	}

	public List<IrrInfinitivoBean> seleccionaPorVerbopartic(String verboId, Particularidad partic) {
		List<IrrInfinitivoBean> resultado=irrInfinitivosDAO.seleccionaPorVerbopartic(verboId, partic);
		return resultado;
	}

	public List<IrrInfinitivoBean> seleccionaPorForma(String forma) {
		List<IrrInfinitivoBean> resultado=irrInfinitivosDAO.seleccionaPorForma(forma);
		return resultado;
	}	
	
	public List<?> reseleccionar() {
		throw new RuntimeException("reselección no implementada todavía");
	}

	public void setUltimosParametros(Object[] parametros) {
		// TODO Auto-generated method stub

	}

	public Object[] getUltimosParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUltimaSeleccion() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUltimaSeleccion(String query) {
		// TODO Auto-generated method stub

	}

}
