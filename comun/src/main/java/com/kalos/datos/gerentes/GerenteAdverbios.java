package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.AdverbioBean;

public interface GerenteAdverbios {
	
	/**
	 * selecciona los ids de todas las entradas de adverbio correspondientes a esa letra
	 * @param letra
	 * @return
	 */
	List<String> seleccionaPorLetra(String letra);
	
	
	/**
	 * Devuelve los ids de los adverbios cuya forma canónica es el parámetro
	 * @param canonica
	 * @return
	 */
	List<String> seleccionaPorCanonica(String canonica);
	
	/**
	 * Devuelve los beans enteros (aunque sin el significado) dada la forma.
	 * Usado para el análisis morfológico.
	 * @param forma
	 * @return
	 */
	List<AdverbioBean> seleccionaPorFormaSinSignificado(String forma);
	
	
	/**
	 * inserta una entrada de adverbio, incluyendo sus significados si los hay, en la
	 * base de datos
	 * @param ea
	 */
	void inserta(AdverbioBean ea);
	
	/**
	 * borra una entrada de adverbio, incluyendo sus significados si los hay, en la
	 * base de datos
	 * @param ea
	 */
	void borra(String Id);
	
	/**
	 * modifica una entrada de adverbio, incluyendo sus significados si los hay, en la
	 * base de datos
	 * @param ea
	 */
	void actualiza(AdverbioBean ea);
	
	/**
	 * selecciona una entrada de adverbio en particular, incluyendo sus significados si los hay, de la
	 * base de datos
	 * @param ea
	 */
	AdverbioBean seleccionaUno(String id);
	
	
	List<AdverbioBean> getBeans(List<String> ids);
	

	
}
