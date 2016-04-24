package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.ParticulaBean;
import com.kalos.enumeraciones.TipoPalabra;

public interface ParticulasDAO  {

	
	/**
	 * obtiene todas las partículas de ese tipo
	 * @param tipo el tipo de palabra
	 * @return
	 */
	List<ParticulaBean> seleccionaParticulasDadoTipoSinSignificado(TipoPalabra tipo);
	
	List<ParticulaBean> seleccionaParticulasDadoEncabezadoSinSignificado(String idEncabezado);
	
	ParticulaBean seleccionaIndividual(String id);
	
	List<ParticulaBean> seleccionaParticulasDadaFormaSinSignificado(String forma);

	public List<ParticulaBean> seleccionaTodo() ;

	
	List<ParticulaBean> seleccionaParticulasNoAcentuablesSinSignificado();
	
	void inserta(ParticulaBean bean);
	
	void modifica(ParticulaBean bean);
	
	void borra(String id);
	
	List<String> seleccionaTodosLosIds();
	
	List<String> seleccionaIdsPorTipo(TipoPalabra tipoPalabra);
	
	List<String> seleccionaIdsPorEncabezado(String idEncabezado);
	
	List<ParticulaBean> getRegistros(List<String> ids);
}