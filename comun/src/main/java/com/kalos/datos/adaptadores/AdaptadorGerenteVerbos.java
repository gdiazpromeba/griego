package com.kalos.datos.adaptadores;

import java.util.List;

import com.kalos.beans.TipoJerarquico;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.enumeraciones.LugarSubcadena;

import org.apache.log4j.Logger;

public class AdaptadorGerenteVerbos implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteVerbos gerenteVerbos;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteVerbos(GerenteVerbos verbosDAO){
		this.gerenteVerbos=verbosDAO;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param cadenaNeutralizada
	 * @param lugarSubcadena
	 */
	public void seleccionPorLetraInicial(String letra){
		this.ids=gerenteVerbos.seleccionaPorLetra(letra);
	}
	
	/**
	 * selecciona por verbo o una subcadena de éste
	 * @param subcadena   la subcadena en código beta
	 * @param lus         theEnumeración que indica el lugar
	 */
	public void seleccionPorSubcadenaVerbo(String subcadena, LugarSubcadena lus){
		logger.debug("selección por subcadena");
		this.ids=gerenteVerbos.seleccionaPorVerbo(subcadena, lus);
	}
	
	public void seleccionPorTipos(List<TipoJerarquico> tipos){
		this.ids=gerenteVerbos.seleccionaPorTipos(tipos);
	}
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
		logger.debug("recibo un pedido de beans - verbos");
		List<?> beans=gerenteVerbos.getBeans(ids);
		logger.debug("entrego beans - verbos");
		return beans;
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}

	public void setVerbosDAO(GerenteVerbos verbosDAO) {
		this.gerenteVerbos = verbosDAO;
	}
}
