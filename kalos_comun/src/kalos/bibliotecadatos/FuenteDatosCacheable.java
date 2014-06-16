package kalos.bibliotecadatos;

import java.util.List;

/**
 * Interfaz que toda fuente de datos cacheable debe implementar.
 * @author gonzalo
 *
 */


public interface FuenteDatosCacheable {
	
	/**
	 * devuelve todos los id's de la lista de la última consulta realizada
	 * @return
	 */
	List<String> getTodosLosId();
	
	/**
	 * devuelve los beans correspondientes al grupo de ids pasado como parámetro
	 * @param ids
	 * @return
	 */
	List<?> getBeans(List<String> ids);
	
	/**
	 * la longitud total de la lista de beans, que necesita ser averiguada rápidamente, antes de empezar a cachear beans
	 * @return
	 */
	int getLongitudTotal();

}
