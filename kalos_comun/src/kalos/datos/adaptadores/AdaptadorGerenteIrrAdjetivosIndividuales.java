package kalos.datos.adaptadores;

import java.util.List;

import kalos.beans.IrrAdjetivoIndividual;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales;

import org.apache.log4j.Logger;

public class AdaptadorGerenteIrrAdjetivosIndividuales implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteIrrAdjetivosIndividuales gerenteIrrAdjetivosIndividuales;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteIrrAdjetivosIndividuales(GerenteIrrAdjetivosIndividuales gerenteIrrAdjetivosIndividuales){
		this.gerenteIrrAdjetivosIndividuales=gerenteIrrAdjetivosIndividuales;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param cadenaNeutralizada
	 * @param lugarSubcadena
	 */
	public void seleccionaPorAdjetivo(String idAdjetivo){
		this.ids=gerenteIrrAdjetivosIndividuales.seleccionaPorAdjetivo(idAdjetivo);
	}
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<IrrAdjetivoIndividual> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<IrrAdjetivoIndividual> beans=gerenteIrrAdjetivosIndividuales.getBeans(ids);
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
