package kalos.datos.adaptadores;

import java.util.List;

import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerenteIrrVerbos;
import kalos.enumeraciones.Particularidad;

import org.apache.log4j.Logger;

public class AdaptadorGerenteIrrVerbos implements FuenteDatosCacheable{

	private List<String> ids;
	private GerenteIrrVerbos gerenteIrrVerbos;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteIrrVerbos(GerenteIrrVerbos gerenteIrrVerbos){
		this.gerenteIrrVerbos=gerenteIrrVerbos;
		
	}
	
	
	/**
	 * devleuve los ids de las irregularidades
	 * @param idVerbo
	 * @param partic
	 */
	public void seleccionaPorVerbo(String idVerbo, Particularidad partic){
		this.ids=gerenteIrrVerbos.seleccionaIdsPorVerboPartic(idVerbo, partic);
	}
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
		logger.debug("recibo un pedido de beans - irr verbos");
		List<?> beans=gerenteIrrVerbos.getBeans(ids);
		return beans;
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}


}
