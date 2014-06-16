package kalos.datos.adaptadores;

import java.util.List;

import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerentePreposiciones;

import org.apache.log4j.Logger;

public class AdaptadorGerentePreposiciones implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerentePreposiciones gerentePreposiciones;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerentePreposiciones(GerentePreposiciones gerentePreposiciones){
		this.gerentePreposiciones=gerentePreposiciones;
		
	}
	
	
	
    public void seleccionaTodosLosIds(){
        this.ids=gerentePreposiciones.seleccionaTodosLosIds();
    }
	
	
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
		logger.debug("recibo un pedido de beans - preposiciones");
		List<?> beans=gerentePreposiciones.getBeans(ids);
		logger.debug("entrego beans");
		return beans;
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}


}
