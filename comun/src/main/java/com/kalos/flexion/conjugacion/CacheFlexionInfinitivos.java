package com.kalos.flexion.conjugacion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.kalos.enumeraciones.Particularidad;
import com.kalos.flexion.conjugacion.negocio.OcurrenciaInfinitivo;

/**
 * Caché que guarda una cierta cantidad resultados de mapas de particularidades, (las cuales son el resultado devuelto por el 
 * método conjuga de Infinitivos). 
 * @author gdiaz
 *
 */
public class CacheFlexionInfinitivos {

	private final int guardadas=10;
	
	private Map<String, Map<Particularidad, OcurrenciaInfinitivo>> mapaDeMapas=new HashMap<String, Map<Particularidad, OcurrenciaInfinitivo>>(); 
	private Queue<String> idsVerbo=new LinkedList<String>();
	

	public Map<Particularidad, OcurrenciaInfinitivo> getMapaOcurrencias(String idVerbo){
		Map<Particularidad, OcurrenciaInfinitivo> mapaOcurrencias=mapaDeMapas.get(idVerbo);
		return mapaOcurrencias;
	}
	
	public OcurrenciaInfinitivo getOcurrencia(String idVerbo, Particularidad particularidad){
		Map<Particularidad, OcurrenciaInfinitivo> mapaOcurrencias=mapaDeMapas.get(idVerbo);
		if (mapaOcurrencias==null)
			return null;
		return mapaOcurrencias.get(particularidad);
	}
	
	public void setOcurrencia(String idVerbo, Particularidad particularidad, OcurrenciaInfinitivo ocurrencia){
		idsVerbo.offer(idVerbo);
		Map<Particularidad, OcurrenciaInfinitivo> map=mapaDeMapas.get(idVerbo);
		if (map==null){
			map=new HashMap<Particularidad, OcurrenciaInfinitivo>();
			mapaDeMapas.put(idVerbo, map);
		}
		map.put(particularidad, ocurrencia);
		if (idsVerbo.size()>guardadas){
			String idVerboASacar=idsVerbo.poll();
			mapaDeMapas.remove(idVerboASacar);
		}
	}
	

}
