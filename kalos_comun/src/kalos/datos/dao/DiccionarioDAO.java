package kalos.datos.dao;

import java.util.List;

import kalos.beans.EntradaDiccionario;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.TipoPalabra;

public interface DiccionarioDAO {

	/**
	 * dada una subcadena neutralizada, devuelve los IDs de todos los
	 * registros de la tabla cuyas formas neutralizadas coindicen con ella 
	 * por delante, atrás o el medio.
     * Dichas formas neutralizadas DEBEN tener el significado adjunto en el idioma del entorno
	 * @param neutralizada
	 * @param lugar
	 * @return
	 */
	public abstract List<String> seleccionaPorNeutralizada(String neutralizada,
			LugarSubcadena lugar, List<TipoPalabra> tipos);
	
	public abstract List<String> seleccionaPorNormal(String neutralizada,
			LugarSubcadena lugar, List<TipoPalabra> tipos);

	public abstract List<String> seleccionaPorSinLargas(String neutralizada,
			LugarSubcadena lugar, List<TipoPalabra> tipos);

	
	/**
	 * selecciona palabras del diccionario exclusivamente por los tipos de palabra
	 * @param tipos
	 * @return
	 */
	public List<String> seleccionaPorTipos(List<TipoPalabra> tipos);
	
	/**
	 * selección de entradas de diccionario por letra inicial
	 * @param letra
	 * @return
	 */
	public List<String> seleccionaPorLetra(String letra);
	
	/**
	 * devuelve beans del tipo "entrada diccionario" dados los ids
	 * @param ids
	 * @return
	 */
	public abstract List<EntradaDiccionario> getRegistros(List<String> ids);
	
	
	/**
	 * modifica un código (para reordenamientos)
	 * @param nuevoCodigo
	 * @param idReferente
	 */
	void modificaCodigoIndividual(int nuevoCodigo, String idReferente);
	
	void inserta(EntradaDiccionario end);
	
	

}