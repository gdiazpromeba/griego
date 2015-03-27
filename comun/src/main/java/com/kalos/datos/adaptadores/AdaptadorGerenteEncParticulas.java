package com.kalos.datos.adaptadores;

import java.util.ArrayList;
import java.util.List;

import com.kalos.beans.EncParticulaBean;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteEncParticulasImpl;

import org.apache.log4j.Logger;

public class AdaptadorGerenteEncParticulas implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteEncParticulasImpl gerenteEncParticulas;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteEncParticulas(GerenteEncParticulasImpl gerente){
		this.gerenteEncParticulas=gerente;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param letra
	 */
	public void seleccionaTodos(){
		List<EncParticulaBean>beans=gerenteEncParticulas.getTodos();
		List<String> ids=new ArrayList<String>();
		for (EncParticulaBean bean: beans){
			ids.add(bean.getId());
		}
		this.ids=ids;
	}
	

	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<EncParticulaBean> getBeans(List<String> ids) {
		//TODO : rápido y sucio
		return gerenteEncParticulas.getTodos();
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}

}
