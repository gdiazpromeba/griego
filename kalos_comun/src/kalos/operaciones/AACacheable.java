/*
 * Created on Dec 9, 2004
 */
package kalos.operaciones;

import java.util.HashMap;
import java.util.Map;

/**
 * Crea un envoltorio para Análisis Acento, que cachea todos los valores que va produciendo durante 
 * la vida del envoltorio. Ideal para los análisis morfológicos, que repiten mucho los análisis de una misma forma.
 * Sería recomendable crear una instancia de este envoltorio al principio del análisis de una forma, y
 * destruirlo al final
 * 
 *  @author Gonzalo
 *
 */
public class AACacheable {
    private Map<String, AnalisisAcento> mapHallazgos=new HashMap<String, AnalisisAcento>();
    
    /**
     * devuelve un análisis acento, que trata de buscar en el caché primero
     * @param forma
     * @return
     */
    public AnalisisAcento getAnalisisAcento(String forma){
        AnalisisAcento aa=(AnalisisAcento)mapHallazgos.get(forma);
        if (aa==null){
            aa=AnalisisAcento.getAnalisisAcento(forma);
            mapHallazgos.put(forma, aa);
        }
        return aa;
    }
    
    
}
