package kalos.flexion.conjugacion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import kalos.enumeraciones.Particularidad;
import kalos.flexion.conjugacion.negocio.Ocurrencia;

/**
 * Caché que guarda una cierta cantidad resultados de mapas de particularidades, (las cuales son el resultado devuelto por el 
 * método flexiona de participios). 
 * @author gdiaz
 *
 */
public class CacheFlexionVerbos {

	private final int guardadas=10;
	
	private Map<String, Map<Particularidad, Ocurrencia>> mapaDeMapas=new HashMap<String, Map<Particularidad, Ocurrencia>>(); 
	private Queue<String> idsVerbo=new LinkedList<String>();
	

	public Map<Particularidad, Ocurrencia> getMapaOcurrencias(String idVerbo){
		Map<Particularidad, Ocurrencia> mapaOcurrencias=mapaDeMapas.get(idVerbo);
		return mapaOcurrencias;
	}
	
	public Ocurrencia getOcurrencia(String idVerbo, Particularidad particularidad){
		Map<Particularidad, Ocurrencia> mapaOcurrencias=mapaDeMapas.get(idVerbo);
		if (mapaOcurrencias==null)
			return null;
		return mapaOcurrencias.get(particularidad);
	}
	
	public void setOcurrencia(String idVerbo, Particularidad particularidad, Ocurrencia ocurrencia){
		idsVerbo.offer(idVerbo);
		Map<Particularidad, Ocurrencia> map=mapaDeMapas.get(idVerbo);
		if (map==null){
			map=new HashMap<Particularidad, Ocurrencia>();
			mapaDeMapas.put(idVerbo, map);
		}
		map.put(particularidad, ocurrencia);
		if (idsVerbo.size()>guardadas){
			String idVerboASacar=idsVerbo.poll();
			mapaDeMapas.remove(idVerboASacar);
		}
	}
	
	/**
	 * limpia todo el caché
	 */
	public void limpia(){
		mapaDeMapas.clear();
	}
	

}
