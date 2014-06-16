package kalos.datos.adaptadores;

import java.util.List;

import kalos.beans.SustantivoBean;
import kalos.beans.TipoJerarquico;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerenteSustantivos;
import kalos.enumeraciones.LugarSubcadena;

import org.apache.log4j.Logger;

public class AdaptadorGerenteSustantivos implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteSustantivos gerenteSustantivos;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteSustantivos(GerenteSustantivos gerente){
		this.gerenteSustantivos=gerente;
		
	}
	
	
	/**
	 * Llama a la fuente de datos y devuelve todos los ids de la consulta.
	 * Debe ser llamado antes de empezar a utilizar (mover) el caché
	 * @param letra
	 */
	public void seleccionPorLetraInicial(String letra){
		this.ids=gerenteSustantivos.seleccionaPorLetra(letra);
	}
	

	public void seleccionPorTipos(List<TipoJerarquico> tipos){
		this.ids=gerenteSustantivos.seleccionaPorTipos(tipos);
	}
	
	
	public void seleccionaPorNominativo(String nominativo, LugarSubcadena lus){
		this.ids=gerenteSustantivos.seleccionaPorNominativo(nominativo, lus);
	}

	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<SustantivoBean> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<SustantivoBean> beans=gerenteSustantivos.getBeans(ids);
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
