package com.kalos.datos.adaptadores;

import java.util.List;

import com.kalos.beans.DesinSust;
import com.kalos.beans.TipoJerarquico;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteDesinSust;

import org.apache.log4j.Logger;

public class AdaptadorGerenteDesinSust implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteDesinSust gerenteDesinSust;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteDesinSust(GerenteDesinSust gerente){
		this.gerenteDesinSust=gerente;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param letra
	 */
	public void seleccionaPorTipos(String[] idsTipos, boolean todas ){
		this.ids=gerenteDesinSust.seleccionaPorTipos(idsTipos, todas);
	}
	

	public void seleccionaPorTipos(List<TipoJerarquico> tipos, boolean todas){
		this.ids=gerenteDesinSust.seleccionaPorTipos(tipos, todas);
	}
	

	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<DesinSust> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<DesinSust> beans=gerenteDesinSust.getBeans(ids);
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
