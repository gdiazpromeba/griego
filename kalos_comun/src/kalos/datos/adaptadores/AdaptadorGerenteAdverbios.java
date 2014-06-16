package kalos.datos.adaptadores;

import java.util.List;

import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerenteAdverbios;

import org.apache.log4j.Logger;

public class AdaptadorGerenteAdverbios implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteAdverbios gerenteAdverbios;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteAdverbios(GerenteAdverbios gerenteAdverbios){
		this.gerenteAdverbios=gerenteAdverbios;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param cadenaNeutralizada
	 * @param lugarSubcadena
	 */
	public void seleccionaPorLetraInicial(String letra){
		this.ids=gerenteAdverbios.seleccionaPorLetra(letra);
	}
	
	/**
	 * selecciona por canonica o una subcadena de éste
	 * @param subcadena   la subcadena en código beta
	 * @param lus         theEnumeración que indica el lugar
	 */
	public void seleccionaPorCanonica(String subcadena){
		this.ids=gerenteAdverbios.seleccionaPorCanonica(subcadena);
	}
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<?> beans=gerenteAdverbios.getBeans(ids);
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
