package com.kalos.datos.adaptadores;

import java.util.List;

import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteParticulas;
import com.kalos.enumeraciones.TipoPalabra;

import org.apache.log4j.Logger;

public class AdaptadorGerenteParticulas implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteParticulas gerenteParticulas;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteParticulas(GerenteParticulas gerenteParticulas){
		this.gerenteParticulas=gerenteParticulas;
		
	}
	
	
	
    public void seleccionaTodosLosIds(){
        this.ids=gerenteParticulas.seleccionaTodosLosIds();
    }
    
    
    public void seleccionaIdsPorTipo(TipoPalabra tipo){
        this.ids=gerenteParticulas.seleccionaIdsPorTipo(tipo);
    }
	
	
    public void seleccionaIdsPorEncabezado(String idEncabezado){
        this.ids=gerenteParticulas.seleccionaIdsPorEncabezado(idEncabezado);
    }
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<?> beans=gerenteParticulas.getBeans(ids);
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
