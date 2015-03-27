package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.ParticulaBean;
import com.kalos.enumeraciones.TipoPalabra;

public interface GerenteParticulas {
	

	List<ParticulaBean> seleccionaParticulasDadoEncabezadoSinSignificado(String encabezado, boolean duales);
	
	List<ParticulaBean> seleccionaParticulasDadoTipoSinSignificado(TipoPalabra tipo, boolean duales);
	
	List<ParticulaBean> seleccionaParticulasDadaFormaSinSignificado(String forma);
	
	List<ParticulaBean> seleccionaParticulasNoAcentuablesSinSignificado();
	
	ParticulaBean getParticulaSinSignificado(String id);
	
	void inserta(ParticulaBean bean);
	
	void modifica(ParticulaBean bean);
	
	void borra(String id);
	
	List<String> seleccionaTodosLosIds();
	
	List<String> seleccionaIdsPorTipo(TipoPalabra tipoPalabra);
	
	List<String> seleccionaIdsPorEncabezado(String idEncabezado);
	
	List<ParticulaBean> getBeans(List<String> ids);
	
}
