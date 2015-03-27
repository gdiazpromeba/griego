package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.IrrAdjetivoEntero;
import com.kalos.datos.dao.IrrAdjetivosEnterosDAO;

public class GerenteIrrAdjetivosEnterosImpl implements GerenteIrrAdjetivosEnteros{

	private String ultimaSeleccion;
	private Object[] ultimosParametros;
	private IrrAdjetivosEnterosDAO irrAdjetivosEnterosDAO;
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#seleccionaPorAdjetivo(java.lang.String)
	 */
	public List<String> seleccionaPorAdjetivo(String adjetivoId){
		setUltimosParametros(new Object[]{adjetivoId});
		setUltimaSeleccion("seleccionaPorAdjetivo");
		List<String> irrs=irrAdjetivosEnterosDAO.seleccionaPorAdjetivo(adjetivoId);
		return irrs;
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#seleccionIndividual(java.lang.String)
	 */
	public IrrAdjetivoEntero seleccionIndividual(String irrAdjetivoEnteroId){
		setUltimosParametros(new Object[]{irrAdjetivoEnteroId});
		setUltimaSeleccion("seleccionIndividual");
		IrrAdjetivoEntero iae=irrAdjetivosEnterosDAO.seleccionIndividual(irrAdjetivoEnteroId);
		return iae;
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#inserta(kalos.beans.IrrAdjetivoEntero)
	 */
	public void inserta(IrrAdjetivoEntero iae){
		irrAdjetivosEnterosDAO.inserta(iae);
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#modifica(kalos.beans.IrrAdjetivoEntero)
	 */
	public void modifica(IrrAdjetivoEntero bean){
	  irrAdjetivosEnterosDAO.modifica(bean);
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#borra(java.lang.String)
	 */
	public void borra(String irrAdjetivoEnteroId){
		irrAdjetivosEnterosDAO.borra(irrAdjetivoEnteroId);
	}
	
	public void borraAdjetivo(String id){
	    irrAdjetivosEnterosDAO.borraAdjetivo(id);
	}
	
    public List<IrrAdjetivoEntero> getBeans(List<String> ids){
    	return irrAdjetivosEnterosDAO.getRegistros(ids);
    }

	
    
	
	public List<String> reseleccionar(){
		if (ultimaSeleccion.endsWith("seleccionaPorAdjetivo")){
			return seleccionaPorAdjetivo((String)ultimosParametros[0]);
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
	 * @param irrAdjetivosEnterosDAO The irrAdjetivosEnterosDAO to set.
	 */
	public void setIrrAdjetivosEnterosDAO(IrrAdjetivosEnterosDAO irrAdjetivosEnterosDAO) {
		this.irrAdjetivosEnterosDAO = irrAdjetivosEnterosDAO;
	}

}