package kalos.flexion.declinacion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import kalos.enumeraciones.Particularidad;

/**
 * Caché que guarda una cierta cantidad resultados de mapas de particularidades, (las cuales son el resultado devuelto por el 
 * método flexiona de participios). 
 * @author gdiaz
 *
 */
public class CacheFlexionParticipio {

//	Logger logger=Logger.getLogger(this.getClass().getName());
	
	private final int guardadas=10;
	
	/**
	 * un mapa que guarda como clave el ID del verbo, y como valor, un mapa de particularidades-ocurrencias
	 */
	private Map<String, Map<Particularidad, OcParticipio>> mapaDeMapas=new HashMap<String, Map<Particularidad, OcParticipio>>(); 
	private Queue<String> idsVerbo=new LinkedList<String>();
	

	public Map<Particularidad, OcParticipio> getMapaOcurrencias(String idVerbo){
		Map<Particularidad, OcParticipio> mapaOcurrencias=mapaDeMapas.get(idVerbo);
		return mapaOcurrencias;
	}
	
	
	/**
	 * Puebla el mapa interno con valores de mapa de ocurrencia
	 * Mantiene la fila adecuadamente para que nunca se almacenen mas de "cantidad" mapas 
	 * de cada tipo
	 * @param idVerbo
	 * @param mapaOcurrencias
	 */
	public void setMapaOcurrencias(String idVerbo, Map<Particularidad, OcParticipio> mapaOcurrencias){
		idsVerbo.offer(idVerbo);
		mapaDeMapas.put(idVerbo, mapaOcurrencias);
		if (idsVerbo.size()>guardadas){
			String idVerboASacar=idsVerbo.poll();
			mapaDeMapas.remove(idVerboASacar);
		}
		
	}
	

}
