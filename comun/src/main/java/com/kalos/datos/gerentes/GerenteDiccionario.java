package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.EntradaDiccionario;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.TipoPalabra;

public interface GerenteDiccionario {


	
	

	/**
	 * modifica el código de una entrada (usado en reordenamientos)
	 * @param nuevoCodigo
	 * @param idReferente
	 */
	public abstract void modificaCodigoIndividual(int nuevoCodigo,
			String idReferente);
	
	/**
	 * selecciona las entradas de diccionario que comienzan con esa letra, ordenadas por el campo
	 * código de la tabla diccionario
	 * @param letra
	 * @return
	 */
	public List<String> seleccionaPorLetra(String letra);

	
	/**
	 * selecciona formas canónicas que contengan una subcadena
	 * @param subcadena
	 * @param lugar
	 * @return
	 */
	List<String> seleccionaPorNeutralizada(String subcadena, LugarSubcadena lugar, List<TipoPalabra> tipos);
	
	List<String> seleccionaPorNormal(String subcadena, LugarSubcadena lugar, List<TipoPalabra> tipos);
	
	List<String> seleccionaPorSinLargas(String subcadena, LugarSubcadena lugar, List<TipoPalabra> tipos);
	
	
	/**
	 * selecciona palabras del diccionario exclusivamente por los tipos de palabra
	 * @param tipos
	 * @return
	 */
	public List<String> seleccionaPorTipos(List<TipoPalabra> tipos);
	
	
	List<EntradaDiccionario> getRegistros(List<String> ids);
	
	void inserta(EntradaDiccionario ea);
	
	
}