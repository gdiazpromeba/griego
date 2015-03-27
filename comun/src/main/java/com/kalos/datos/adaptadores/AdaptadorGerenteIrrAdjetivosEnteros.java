package com.kalos.datos.adaptadores;

import java.util.List;

import com.kalos.beans.IrrAdjetivoEntero;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosEnteros;

import org.apache.log4j.Logger;

public class AdaptadorGerenteIrrAdjetivosEnteros implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteIrrAdjetivosEnteros gerenteIrrAdjetivosEnteros;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteIrrAdjetivosEnteros(GerenteIrrAdjetivosEnteros gerenteIrrAdjetivosEnteros){
		this.gerenteIrrAdjetivosEnteros=gerenteIrrAdjetivosEnteros;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param cadenaNeutralizada
	 * @param lugarSubcadena
	 */
	public void seleccionaPorAdjetivo(String idAdjetivo){
		this.ids=gerenteIrrAdjetivosEnteros.seleccionaPorAdjetivo(idAdjetivo);
	}
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<IrrAdjetivoEntero> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<IrrAdjetivoEntero> beans=gerenteIrrAdjetivosEnteros.getBeans(ids);
//		logger.debug("entrego beans");
		return beans;
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}


}
