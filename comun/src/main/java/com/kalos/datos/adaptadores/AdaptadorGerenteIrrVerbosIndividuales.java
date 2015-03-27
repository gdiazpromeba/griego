package com.kalos.datos.adaptadores;

import java.util.List;

import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.enumeraciones.Particularidad;

import org.apache.log4j.Logger;

public class AdaptadorGerenteIrrVerbosIndividuales implements FuenteDatosCacheable{

	private List<String> ids;
	private GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteIrrVerbosIndividuales(GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales){
		this.gerenteIrrVerbosIndividuales=gerenteIrrVerbosIndividuales;
		
	}
	
	
	/**
	 * devleuve los ids de las irregularidades
	 * @param idVerbo
	 * @param partic
	 */
	public void seleccionaPorVerbo(String idVerbo, Particularidad partic){
		this.ids=gerenteIrrVerbosIndividuales.seleccionaIdsPorVerboPartic(idVerbo, partic);
	}
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
		List<?> beans=gerenteIrrVerbosIndividuales.getBeans(ids);
		return beans;
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}


}
