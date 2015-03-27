package com.kalos.datos.gerentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.TiposAdjetivoDAO;

public class GerenteTiposAdjetivoImpl implements GerenteTiposAdjetivo{

	private String ultimaSeleccion;
	private Object[] ultimosParametros;
	private TiposAdjetivoDAO tiposAdjetivoDAO;
	
	public List<TipoJerarquico> getTodos() {
		List<TipoJerarquico> tipos=tiposAdjetivoDAO.getTodos();
		setUltimaSeleccion("getTodos");
		setUltimosParametros(new Object[]{});
		return tipos;
	}
	
	public Map<Integer, String> getMapaTiposID(){
		List<TipoJerarquico> tipos=tiposAdjetivoDAO.getTodos();
		Map<Integer, String> resultado=new HashMap<Integer, String>();
		for (TipoJerarquico tij: tipos){
			resultado.put(tij.getValorEntero(), tij.getId());
		}
		return  resultado;
	}
	
	public List<TipoJerarquico> reseleccionar(){
		if (ultimaSeleccion.endsWith("getTodos")){
			return getTodos();
		}else{
			return null;
		}
	}

	/**
	 * @return Returns the ultimaSeleccion.
	 */
	public String getUltimaSeleccion() {
		return ultimaSeleccion;
	}

	/**
	 * @param ultimaSeleccion The ultimaSeleccion to set.
	 */
	public void setUltimaSeleccion(String ultimaSeleccion) {
		this.ultimaSeleccion = ultimaSeleccion;
	}

	/**
	 * @return Returns the ultimosParametros.
	 */
	public Object[] getUltimosParametros() {
		return ultimosParametros;
	}

	/**
	 * @param ultimosParametros The ultimosParametros to set.
	 */
	public void setUltimosParametros(Object[] ultimosParametros) {
		this.ultimosParametros = ultimosParametros;
	}

	/**
	 * @return Returns the tiposAdjetivoDAO.
	 */
	public TiposAdjetivoDAO getTiposAdjetivoDAO() {
		return tiposAdjetivoDAO;
	}

	/**
	 * @param tiposAdjetivoDAO The tiposAdjetivoDAO to set.
	 */
	public void setTiposAdjetivoDAO(TiposAdjetivoDAO tiposAdjetivoDAO) {
		this.tiposAdjetivoDAO = tiposAdjetivoDAO;
	}

}
