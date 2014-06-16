package kalos.operaciones;

import java.util.Map;

import kalos.beans.ISignificados;
import kalos.beans.Significado;
import kalos.enumeraciones.Idioma;
import kalos.recursos.Configuracion;

import org.apache.commons.beanutils.BeanUtils;


/**
 * clase utilitaria para lidiar con lo significados en los beans
 * @author gdiaz
 *
 */
public class OpSignificados {
	
//	private static Logger logger=Logger.getLogger(OpSignificados.class.getName());
	
	private static Idioma idioma=Idioma.getEnum(Configuracion.getIdiomaSignificados());
	
	/**
	 * inserta un significado de un idioma en particular en un bean que soporte la 
	 * interfaz ISignificados
	 * @param bean
	 * @param sig
	 */
	public static void setSignificadoIndividual(ISignificados bean, Significado sig){
		Map<Idioma, Significado> map=bean.getSignificados();
		Idioma idioma=Idioma.getEnum(sig.getIdioma());
		map.put(idioma, sig);
	}
	
	/**
	 * puebla un idioma específico del mapa de significados.
	 * Si no existía lo crea, pero los ID de significado serán nulos
	 * @param bean
	 * @param propiedadPK
	 * @param idioma
	 * @param texto
	 */
	public static void setSignificado(ISignificados bean, String propiedadPK, Idioma idioma, String texto) {
		try {
			Map<Idioma, Significado> map = bean.getSignificados();
			Significado sig = map.get(idioma);
			if (sig == null){
				sig = new Significado();
				map.put(idioma, sig);
			}
			String referenteId = BeanUtils.getProperty(bean, propiedadPK);
			sig.setIdioma(Idioma.getString(idioma));
			sig.setReferenteId(referenteId);
			sig.setValor(texto);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * a todos los significados de un bean recientemente creado les agrega el referenteId
	 * @param bean
	 */
	public static void completaReferente(ISignificados bean){
		Map<Idioma, Significado> map = bean.getSignificados();
		for (Map.Entry<Idioma, Significado> entry: map.entrySet()){
			Significado sig=entry.getValue();
			sig.setReferenteId(OpBeans.getId(bean));
		}
	}
	
	/**
	 * devuelve un significado en particular (de un idioma) de un bean que soporta
	 * la interfaz Isignificados
	 * @param bean
	 * @param idioma
	 * @return
	 */
	public static Significado getSignificadoIndividual(ISignificados bean, Idioma idioma){
		Map<Idioma, Significado> map=bean.getSignificados();
		Significado sig=map.get(idioma);
		return sig;
	}

	/**
	 * devuelve el significado del idioma configurado (es decir, sin que haga falta especificar el idioma),
	 * de cualquier bean que soporte la interfaz ISignificado
	 * @param bean
	 * @return
	 */
	public static Significado getSignificadoIndividual(ISignificados bean){
		Map<Idioma, Significado> map=bean.getSignificados();
		Significado sig=map.get(idioma);
		return sig;
	}
	
	/**
	 * devuelve el valor (String) de un objeto significado contenido en un bean
	 * @param bean
	 * @return
	 */
	public static String getValorSignificado(ISignificados bean){
		Significado sig=OpSignificados.getSignificadoIndividual(bean);
		if (sig!=null)
	      return sig.getValor();
		else
		  return null;
	}
	
	

}
